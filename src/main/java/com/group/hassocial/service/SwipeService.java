package com.group.hassocial.service;

import com.hassocial.swaggergen.model.SwipeRequest;
import com.hassocial.swaggergen.model.SwipeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SwipeService {

    public SwipeResponse swipe(@Valid SwipeRequest swipeRequest) {

        //BURDA BISILER YAP

        SwipeResponse swipeResponse = new SwipeResponse();
        //RESPONSEU YARAT

        return swipeResponse;
    }
}
