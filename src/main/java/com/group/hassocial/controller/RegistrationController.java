package com.group.hassocial.controller;
import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.exception.InvalidEmailDomainException;
import com.group.hassocial.exception.UserAlreadyExistException;
import com.group.hassocial.service.RegistrationService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;


@Api(value = "User Registration Rest Controller")
@RestController
@RequestMapping("/api/user")
public class RegistrationController {

    private final RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException,
            InvalidEmailDomainException, ParseException {
         registrationService.register(userDto);
         return ResponseEntity.ok("User is registered half the way. Authentication email is sent!");
    }

    @GetMapping("/authenticate")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto) {
        return "";
    }

    @GetMapping("/test")
    public String getTest() {
        return "Test!";
    }
}