package com.group.hassocial.service;

import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.data.model.User;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.service.interfaces.ISignUpService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

import static com.group.hassocial.data.model.User.datePatternOrganizer;

@Service
public class SignUpService implements ISignUpService {

    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String signUp(UserDto userDto) throws ParseException {
        Optional<User> authenticatedUser = userRepository.findByEmail(userRepository.findAuthenticatedUserEmailByToken());
        if (authenticatedUser.isPresent()) {
            authenticatedUser.get().setFullName(userDto.getFullName());
            authenticatedUser.get().setGender(userDto.isGender());
            authenticatedUser.get().setBirthDate(datePatternOrganizer(userDto.getBirthDate()));
            userRepository.save(authenticatedUser.get());
        }
        return String.format("Hey %s, you are fully signed in!", userDto.getFullName());
    }
}
