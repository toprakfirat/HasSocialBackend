package com.group.hassocial.service;

import com.group.hassocial.data.model.Interest;
import com.group.hassocial.data.model.UserInterest;
import com.group.hassocial.repository.InterestRepository;
import com.group.hassocial.repository.UserInterestRepository;
import com.hassocial.swaggergen.model.BaseInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public void setUserInterests(final int userId, final List<String> interests) {

        final List<Interest> interestsData = interestRepository.findAll();
        final HashMap<String, Integer> interestMap = new HashMap<>();
        interestsData.stream().forEach(interest -> {
            interestMap.put(interest.getInterestName(), interest.getInterestID());
        });

        final List<BaseInterest> userInterests = getUserInterests(userId);
        final List<String> oldInterests = new ArrayList<>();

        userInterests.stream().forEach(ui -> {
            oldInterests.add(ui.getInterestName());

            if (!interests.contains(ui.getInterestName())) {
            userInterestRepository.deleteByUserIdAndInterestId(userId, ui.getInterestId()); }}
            );

        interests.stream().forEach(ui -> {
            if (!oldInterests.contains(ui)){
                final UserInterest userInterest = new UserInterest();
                userInterest.setInterestID(interestMap.get(ui));
                userInterest.setUserID(userId);
                userInterestRepository.save(userInterest); }
        });
    }
}
