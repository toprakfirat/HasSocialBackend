package com.group.hassocial.service;

import com.group.hassocial.data.model.User;
import com.group.hassocial.data.token.AuthenticationToken;
import com.group.hassocial.repository.AuthenticationTokenRepository;
import com.group.hassocial.service.interfaces.IAuthenticationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationTokenService implements IAuthenticationService {

    private final AuthenticationTokenRepository authenticationTokenRepository;

    public AuthenticationTokenService(AuthenticationTokenRepository authenticationTokenRepository) {
        this.authenticationTokenRepository = authenticationTokenRepository;
    }

    @Override
    public void saveAuthenticationToken(User user,  String token) {
        AuthenticationToken authenticationToken = AuthenticationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(60))
                .user(user).build();
        authenticationTokenRepository.save(authenticationToken);
    }

    @Override
    public Optional<AuthenticationToken> getToken(String token) {
        return authenticationTokenRepository.findByToken(token);
    }

    @Override
    public void setAuthenticationTime(String token) {
        authenticationTokenRepository.updateAuthenticationTime(token, LocalDateTime.now());
    }
}
