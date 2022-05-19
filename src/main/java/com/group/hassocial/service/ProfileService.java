package com.group.hassocial.service;

import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.util.UserUtil;
import com.hassocial.swaggergen.model.BaseUser;
import com.hassocial.swaggergen.model.ChangeSettingsRequest;
import com.hassocial.swaggergen.model.ProfileResponse;
import com.hassocial.swaggergen.model.Response;
import com.hassocial.swaggergen.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private InterestsService interestsService;

    public ProfileResponse getProfile() {

        final ProfileResponse profileResponse = new ProfileResponse();

        //TODO Get UserId from session constant after it's implementation
        final int id = 1;

        final BaseUser user = userUtil.userToBaseUser(userRepository.findById(id).get());

        profileResponse.setResult(Result.OK);
        profileResponse.setUser(user);
        profileResponse.setInterests(interestsService.getUserInterests(id));

        return profileResponse;
    }

    public Response changeSettings(final ChangeSettingsRequest request) {

        //TODO Get UserId from session constant after it's implementation
        final int id = 0;
        final BaseUser user = userUtil.userToBaseUser(userRepository.findById(id).get());

        return null;

    }
}
