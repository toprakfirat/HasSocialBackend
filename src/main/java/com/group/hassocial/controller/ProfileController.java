package com.group.hassocial.controller;

import com.hassocial.swaggergen.controller.ProfileApi;
import com.hassocial.swaggergen.model.ProfileResponse;
import com.hassocial.swaggergen.model.SwipeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProfileController implements ProfileApi {

    @Override
    public ResponseEntity<ProfileResponse> getProfile() {

        return null;
    }
}
