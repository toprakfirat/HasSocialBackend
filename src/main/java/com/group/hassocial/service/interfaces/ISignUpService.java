package com.group.hassocial.service.interfaces;

import com.group.hassocial.data.dto.UserDto;

import java.text.ParseException;

public interface ISignUpService {

    String signUp(UserDto userDto) throws ParseException;
}
