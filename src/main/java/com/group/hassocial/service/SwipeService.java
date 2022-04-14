package com.group.hassocial.service;

import com.hassocial.swaggergen.model.Result;
import com.hassocial.swaggergen.model.SwipeRequest;
import com.hassocial.swaggergen.model.SwipeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SwipeService {

    public SwipeResponse swipe(@Valid SwipeRequest swipeRequest) {

        //BURDA BISILER YAP

        //TODO will be implemented when connected with the server
        SwipeResponse swipeResponse = new SwipeResponse();
        swipeResponse.setMatch(false);
        swipeResponse.setResult(Result.OK);
        //RESPONSEU YARAT

        return swipeResponse;
    }
}
