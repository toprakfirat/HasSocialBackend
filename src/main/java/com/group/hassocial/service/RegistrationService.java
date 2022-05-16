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
import com.group.hassocial.service.interfaces.IRegistrationService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service("registrationService")
public class RegistrationService implements IRegistrationService {

    private final UserRepository userRepository;
    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final AuthenticationTokenService authenticationTokenService;
    private final EmailSender emailSender;
    private final EmailValidator emailValidator;

    public RegistrationService(EmailSender emailSender, UserRepository userRepository, AuthenticationTokenRepository authenticationTokenRepository,
                               AuthenticationTokenService authenticationTokenService, EmailValidator emailValidator) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.authenticationTokenRepository = authenticationTokenRepository;
        this.authenticationTokenService = authenticationTokenService;
        this.emailValidator = emailValidator;
    }


    @Override
    public String register(UserDto userDto) throws UserAlreadyExistException, InvalidEmailDomainException {
        if (checkIfUserExist(userDto.getEmail()) && userRepository.findByEmail(userDto.getEmail()).isPresent()) {

            User notAuthenticatedUser = userRepository.findByEmail(userDto.getEmail()).get();

            if (!notAuthenticatedUser.isIsVerified()) {
                String token = UUID.randomUUID().toString();
                saveAuthenticationToken(notAuthenticatedUser, token);

                return token;
            }
            throw new UserAlreadyExistException("User already exists for this email");
        }

        if (!checkIfUniversityEmail(userDto.getEmail()) || emailValidator.test(userDto.getEmail())) {
            throw new InvalidEmailDomainException("Email : %s is not a valid mail!", userDto.getEmail());
        }


        var user = User.builder().Email(userDto.getEmail()).build();
        encodePassword(user, userDto); // encodes user password using hash function
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        saveAuthenticationToken(user, token);

        String link = "http://localhost:8080/api/user/authenticate?token=" + token;
        emailSender.sendEmail(userDto.getEmail(), emailSender.buildEmail(userDto.getFullName(), link));
        return token;
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void encodePassword(User user, UserDto userDto) {
        User.builder().PasswordHash(DigestUtils.sha256Hex(userDto.getPassword()));
    }

    @Override
    public boolean checkIfUniversityEmail(String email) {
        return universityDomains.checksIfDomainIsValid(email.split("@")[1]);
    }

    private void saveAuthenticationToken(User user, String token) {
        AuthenticationToken authenticationToken = new AuthenticationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        authenticationTokenService.saveAuthenticationToken(authenticationToken);
    }

    public void makeUserVerified(String email) {
        userRepository.verifyUser(email);
    }

    @Transactional
    public String confirmToken(String token) {
        Optional<AuthenticationToken> confirmToken = authenticationTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
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

        //Returning confirmation message if the token matches
        return String.format("Hey %s, your email is authenticated", confirmToken.get().getUser().getFullName());
    }
}
