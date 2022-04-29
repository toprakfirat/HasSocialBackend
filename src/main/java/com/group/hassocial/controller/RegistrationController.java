package com.group.hassocial.controller;
import com.group.hassocial.data.model.User;
import com.group.hassocial.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "User Registration Rest Controller")
@RestController
@RequestMapping("/user")
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/register")
    public String testEndpoint(){
        List<User> userList = userRepository.findAll();
        User user = new User();
        userList.forEach((s) -> user.setFullName(s.getFullName()));
        return user.getFullName();
    }
}