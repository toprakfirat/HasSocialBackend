package com.group.hassocial.service.interfaces;

import com.group.hassocial.data.model.User;
import com.group.hassocial.data.token.AuthenticationToken;

import java.util.Optional;

public interface IAuthenticationService {

    public void saveAuthenticationToken(User user, String token);

    public Optional<AuthenticationToken> getToken(String token);

    public void setAuthenticationTime(String token);
}
