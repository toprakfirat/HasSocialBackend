package com.group.hassocial.service;
import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.data.model.User;
import com.group.hassocial.data.token.AuthenticationToken;
import com.group.hassocial.exception.InvalidEmailDomainException;
import com.group.hassocial.exception.UserAlreadyExistException;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.security.EmailValidator;
import com.group.hassocial.security.PasswordEncoder;
import com.group.hassocial.service.interfaces.IRegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service("registrationService")
public class RegistrationService implements IRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationTokenService authenticationTokenService;
    private final EmailSenderService emailSenderService;
    private final EmailValidator emailValidator;
    private final UniversityService universityService;

    @Value("${authentication.link}")
    private String authenticationLink;

    private final String userCreationTime = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE).replace("-", "/");

    public RegistrationService(EmailSenderService emailSenderService, UserRepository userRepository, PasswordEncoder passwordEncoder,
                               AuthenticationTokenService authenticationTokenService, EmailValidator emailValidator, UniversityService universityService) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationTokenService = authenticationTokenService;
        this.emailValidator = emailValidator;
        this.universityService = universityService;
    }


    @Override
    public String register(UserDto userDto) throws UserAlreadyExistException, InvalidEmailDomainException, ParseException {
        if (checkIfUserExist(userDto.getEmail()) && userRepository.findByEmail(userDto.getEmail()).isPresent()) {

            User notAuthenticatedUser = userRepository.findByEmail(userDto.getEmail()).get();

            if (!notAuthenticatedUser.isIsVerified()) {
                String newToken = UUID.randomUUID().toString();
                authenticationTokenService.saveAuthenticationToken(notAuthenticatedUser, newToken);
                emailSenderService.sendEmail(userDto.getEmail(), emailSenderService.buildEmail(notAuthenticatedUser.getFullName(), authenticationLink + newToken));

                return newToken;
            }
            throw new UserAlreadyExistException("User already exists for this email");
        }

        if (!emailValidator.checkIfUniversityEmail(userDto.getEmail())) {
            throw new InvalidEmailDomainException("Email : %s is not a valid mail!", userDto.getEmail());
        }
        final User user = User.builder()
                .Email(userDto.getEmail())
                .PasswordHash(passwordEncoder.encodePassword(userDto))
                .CreateDate(User.datePatternOrganizer(userCreationTime))
                .UniversityID(universityService.extractUniversityIdFromDomain(userDto.getEmail()))
                .build();

            userRepository.save(user);
        String token = UUID.randomUUID().toString();
        authenticationTokenService.saveAuthenticationToken(user, token);

        emailSenderService.sendEmail(userDto.getEmail(), emailSenderService.buildEmail(user.getFullName(), authenticationLink + token));

        return token;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void makeUserVerified(String email) {
        Optional<User> userRepositoryByEmail = userRepository.findByEmail(email);
        userRepositoryByEmail.ifPresent(user -> user.setIsVerified(true));
        //userRepository.verifyUser(email);
    }

    @Transactional
    public String confirmToken(String token) {
        Optional<AuthenticationToken> confirmToken = authenticationTokenService.getToken(token);

        if (!confirmToken.isPresent()) {
            throw new IllegalStateException("Token not found!");
        }
        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed!");
        }
        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }
        authenticationTokenService.setAuthenticationTime(token);
        makeUserVerified(confirmToken.get().getUser().getEmail());

        //TODO: here the user will come back to app to finalize the sign-up.
        return String.format("Hey %s, your email is authenticated", confirmToken.get().getUser().getFullName());
    }

}
