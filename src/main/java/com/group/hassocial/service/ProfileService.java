package com.group.hassocial.service;

import com.group.hassocial.data.dto.UserDto;

import com.group.hassocial.repository.UserRepository;

import com.group.hassocial.util.ModelMapperUtil;
import com.hassocial.swaggergen.model.BaseUser;
import com.hassocial.swaggergen.model.ProfileResponse;
import com.hassocial.swaggergen.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @Autowired
    private InterestsService interestsService;

    public ProfileResponse getProfile() {

        final ProfileResponse profileResponse = new ProfileResponse();

        //TODO Get UserId from session constant after it's implementation
        final int id = 0;

        final BaseUser user = modelMapperUtil.modelMapper.map(userRepository.findById(id).get(), BaseUser.class);

        profileResponse.setResult(Result.OK);
        profileResponse.setUser(user);
        profileResponse.setInterests(interestsService.getUserInterests(id));

        return profileResponse;
    }
}
