package com.group.hassocial.controller;

import com.group.hassocial.service.SwipeService;
import com.hassocial.swaggergen.controller.SwipeApi;
import com.hassocial.swaggergen.model.SwipeRequest;
import com.hassocial.swaggergen.model.SwipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SwipeController implements SwipeApi {

    @Autowired
    private SwipeService swipeService;

    @Override
    public ResponseEntity<SwipeResponse> swipe(@Valid SwipeRequest swipeRequest) {

        final SwipeResponse swipeResponse = swipeService.swipe(swipeRequest);
        //SERVICE RESPONSEU RESPONSE ENTITY'E CEVIR RETURN ET

        //TODO Figure Out Response Entities
        return null;
    }
}
