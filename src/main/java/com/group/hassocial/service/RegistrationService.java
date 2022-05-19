package com.group.hassocial.service;
import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.data.dto.enums.universityDomains;
import com.group.hassocial.data.model.User;
import com.group.hassocial.data.token.AuthenticationToken;
import com.group.hassocial.exception.InvalidEmailDomainException;
import com.group.hassocial.exception.UserAlreadyExistException;
import com.group.hassocial.repository.AuthenticationTokenRepository;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.security.EmailValidator;
import com.group.hassocial.security.PasswordEncoder;
import com.group.hassocial.service.interfaces.IRegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service("registrationService")
public class RegistrationService implements IRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationTokenService authenticationTokenService;
    private final EmailSender emailSender;
    private final EmailValidator emailValidator;

    @Value("${authentication.link}")
    private String authenticationLink;

    public RegistrationService(EmailSender emailSender, UserRepository userRepository, PasswordEncoder passwordEncoder,
                               AuthenticationTokenService authenticationTokenService, EmailValidator emailValidator) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationTokenService = authenticationTokenService;
        this.emailValidator = emailValidator;
    }


    @Override
    public String register(UserDto userDto) throws UserAlreadyExistException, InvalidEmailDomainException, ParseException {
        if (checkIfUserExist(userDto.getEmail()) && userRepository.findByEmail(userDto.getEmail()).isPresent()) {

            User notAuthenticatedUser = userRepository.findByEmail(userDto.getEmail()).get();

            if (!notAuthenticatedUser.isIsVerified()) {
                String newToken = UUID.randomUUID().toString();
                authenticationTokenService.saveAuthenticationToken(notAuthenticatedUser, newToken);
                emailSender.sendEmail(userDto.getEmail(), emailSender.buildEmail(notAuthenticatedUser.getFullName(), authenticationLink + newToken));

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
                .CreateDate(User.datePatternOrganizer("2022/05/18"))
                .UniversityID(1)
                .build();
            userRepository.save(user);

        String token = UUID.randomUUID().toString();
        authenticationTokenService.saveAuthenticationToken(user, token);

        emailSender.sendEmail(userDto.getEmail(), emailSender.buildEmail(userDto.getFullName(), authenticationLink + token));
        return token;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void makeUserVerified(String email) {
        userRepository.verifyUser(email);
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

        return String.format("Hey %s, your email is authenticated", confirmToken.get().getUser().getFullName());
    }
}
