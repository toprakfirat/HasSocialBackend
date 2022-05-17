package com.group.hassocial.service.interfaces;

import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.exception.InvalidEmailDomainException;
import com.group.hassocial.exception.UserAlreadyExistException;

import java.text.ParseException;

public interface IRegistrationService {


    String register(UserDto user) throws UserAlreadyExistException, InvalidEmailDomainException, ParseException;

    boolean checkIfUserExist(String email);

    void makeUserVerified(String email);

}
