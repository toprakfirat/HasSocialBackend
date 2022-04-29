package com.group.hassocial.service;

import com.group.hassocial.model.dto.UserDto;
import com.group.hassocial.repository.UserRepository;
import com.hassocial.swaggergen.model.BaseUser;
import com.hassocial.swaggergen.model.ProfileResponse;
import com.hassocial.swaggergen.model.Result;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public ProfileResponse getProfile() {

        final ProfileResponse profileResponse = new ProfileResponse();

        //TODO Get UserId from session constant after it's implementation
        final int id = 0;

        final BaseUser user = modelMapper.map(userRepository.findById(id).get(), BaseUser.class);

        profileResponse.setResult(Result.OK);
        profileResponse.setUser(user);

        return profileResponse;
    }
}
