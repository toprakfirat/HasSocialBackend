package com.group.hassocial.service;

import com.group.hassocial.data.model.Swipe;
import com.group.hassocial.repository.SwipeRepository;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.service.Matches.MatchAlgorithmService;
import com.group.hassocial.util.UserUtil;
import com.hassocial.swaggergen.model.BaseUser;
import com.hassocial.swaggergen.model.MatchProfileRequest;
import com.hassocial.swaggergen.model.MatchProfileResponse;
import com.hassocial.swaggergen.model.MatchesResponse;
import com.hassocial.swaggergen.model.Result;
import com.hassocial.swaggergen.model.SwipeRequest;
import com.hassocial.swaggergen.model.SwipeResponse;
import com.hassocial.swaggergen.model.SwipeType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.group.hassocial.data.model.User.datePatternOrganizer;

@Service
public class MatchesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SwipeRepository swipeRepository;

    @Autowired
    private InterestsService interestsService;

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

    @SneakyThrows
    public SwipeResponse swipe(SwipeRequest swipeRequest) {
        //TODO will be implemented after session
        final int id = 1;

        final SwipeResponse swipeResponse = new SwipeResponse();
        final Swipe newSwipe = new Swipe();

        final SwipeType swipe = swipeRequest.getSwipeType();
        final int targetId = swipeRequest.getTargetId();
        boolean isSwipedBefore = swipeRepository.checkPreviousMatch(id, targetId);

        if (swipe.getValue().equals(SwipeType.RIGHT.getValue())){
            if (Objects.isNull(isSwipedBefore)) {
                isSwipedBefore = false;
            }
            if (isSwipedBefore && swipe.getValue().equals(SwipeType.RIGHT.getValue())) {
                swipeResponse.setMatch(true);
            } else {
                swipeResponse.setMatch(false);
            }

            newSwipe.setIsAccepted(true);

        } else {
            newSwipe.setIsAccepted(false);
            swipeResponse.setMatch(false);
        }

        final LocalDate date = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        newSwipe.setDate(datePatternOrganizer(date.format(formatter)));
        newSwipe.setUserID(id);
        newSwipe.setTargetID(targetId);
        swipeRepository.save(newSwipe);

        swipeResponse.setResult(Result.OK);

        return swipeResponse;
    }

    public MatchProfileResponse getMatchProfile(MatchProfileRequest matchProfileRequest) {

        final int id = matchProfileRequest.getUserId();
        final MatchProfileResponse matchProfileResponse = new MatchProfileResponse();
        final BaseUser user = userUtil.userToBaseUser(userRepository.findById(id).get());

        matchProfileResponse.setUser(user);
        matchProfileResponse.setInterests(interestsService.getUserInterests(id));
        matchProfileResponse.setResult(Result.OK);

        return matchProfileResponse;
    }
}
