package com.group.hassocial.service.interfaces;

import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.data.model.User;
import com.group.hassocial.exception.InvalidEmailDomainException;
import com.group.hassocial.exception.UserAlreadyExistException;

public interface IRegistrationService {


    String register(UserDto user) throws UserAlreadyExistException, InvalidEmailDomainException;

    boolean checkIfUserExist(String email);

    void encodePassword(User user, UserDto userDto);

    boolean checkIfUniversityEmail(String email);

    void makeUserVerified(String email);

}
