package com.group.hassocial.controller;

import com.group.hassocial.service.MatchesService;
import com.hassocial.swaggergen.controller.MatchesApi;
import com.hassocial.swaggergen.model.MatchProfileRequest;
import com.hassocial.swaggergen.model.MatchProfileResponse;
import com.hassocial.swaggergen.model.MatchesResponse;
import com.hassocial.swaggergen.model.SwipeRequest;
import com.hassocial.swaggergen.model.SwipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchesController implements MatchesApi {

    @Autowired
    private MatchesService matchesService;

    @Override
    public ResponseEntity<MatchesResponse> getMatches() {

        final MatchesResponse matchesResponse = matchesService.getMatches();

        return new ResponseEntity<>(matchesResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SwipeResponse> swipe(SwipeRequest swipeRequest) {

        final SwipeResponse swipeResponse = matchesService.swipe(swipeRequest);

        //TODO Figure Out Response Entities
        return new ResponseEntity<>(swipeResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MatchProfileResponse> getMatchProfile(MatchProfileRequest matchProfileRequest) {

        final MatchProfileResponse matchProfileResponse = matchesService.getMatchProfile(matchProfileRequest);

        return new ResponseEntity<>(matchProfileResponse, HttpStatus.OK);
    }
}
