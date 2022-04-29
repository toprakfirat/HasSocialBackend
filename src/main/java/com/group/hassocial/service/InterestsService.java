package com.group.hassocial.service;

import com.group.hassocial.data.model.Interest;
import com.group.hassocial.data.model.UserInterest;
import com.group.hassocial.repository.InterestRepository;
import com.group.hassocial.repository.UserInterestRepository;
import com.hassocial.swaggergen.model.BaseInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestsService {

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private UserInterestRepository userInterestRepository;

    public List<BaseInterest> getUserInterests(final int userId){

        final List<Interest> interestsData = interestRepository.findAll();
        final List<UserInterest> userInterestsData = userInterestRepository.findByUserID(userId);

        final List<BaseInterest> userInterests = new ArrayList<>();

        userInterestsData.stream().forEach(ui -> {

            final BaseInterest baseInterest = new BaseInterest();
            final int interestId = ui.getInterestID();
            baseInterest.setInterestId(interestId);
            baseInterest.setInterestName(interestsData.get(interestId).getInterestName());

            userInterests.add(baseInterest);
        } );

        return userInterests;
    }
}
