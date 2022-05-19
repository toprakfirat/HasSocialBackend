package com.group.hassocial.service;

import com.group.hassocial.data.model.User;
import com.group.hassocial.service.Matches.MatchAlgorithmService;
import com.group.hassocial.util.UserUtil;
import com.hassocial.swaggergen.model.BaseUser;
import com.hassocial.swaggergen.model.MatchesResponse;
import com.hassocial.swaggergen.model.Result;
import com.hassocial.swaggergen.model.SwipeRequest;
import com.hassocial.swaggergen.model.SwipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class MatchesService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private MatchAlgorithmService matchAlgorithmService;

    public MatchesResponse getMatches() {

        final MatchesResponse matchesResponse = new MatchesResponse();
        final List<BaseUser> users = matchAlgorithmService.matchAlgorithm();

        matchesResponse.setUsers(users);
        matchesResponse.setResult(Result.OK);

        return matchesResponse;
    }

    public SwipeResponse swipe(@Valid SwipeRequest swipeRequest) {

        //TODO will be implemented when connected with the server
        SwipeResponse swipeResponse = new SwipeResponse();
        swipeResponse.setMatch(false);
        swipeResponse.setResult(Result.OK);

        return swipeResponse;
    }
}
