package com.group.hassocial.service;

import com.group.hassocial.data.model.User;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.util.ModelMapperUtil;
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
    private UserRepository userRepository;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    public MatchesResponse getMatches() {

        final MatchesResponse matchesResponse = new MatchesResponse();
        //TODO Get UserId from session constant after it's implementation
        final int id = 1;
        final boolean gender = false;
        final List<User> users = userRepository.findMatches(id, gender).get();
        return null;
    }

    public SwipeResponse swipe(@Valid SwipeRequest swipeRequest) {

        //TODO will be implemented when connected with the server
        SwipeResponse swipeResponse = new SwipeResponse();
        swipeResponse.setMatch(false);
        swipeResponse.setResult(Result.OK);

        return swipeResponse;
    }
}
