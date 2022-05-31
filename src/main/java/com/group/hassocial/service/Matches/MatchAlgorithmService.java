package com.group.hassocial.service.Matches;
import com.group.hassocial.data.model.User;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.util.UserUtil;
import com.hassocial.swaggergen.model.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MatchAlgorithmService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    public final List<BaseUser> matchAlgorithm() {

        //TODO Get UserId from session constant after it's implementation
        final int id = 1;
        final int orientation = 2;
        final boolean gender = false;

        final List<User> users;

        if (orientation == 2) {
            users = userRepository.findMatchesFromBothGender(id).get();
        }
        else {
            users = userRepository.findMatches(id, gender).get();
        }

        final List<BaseUser> baseUsers = new ArrayList<>();

        users.forEach(user -> {
            final BaseUser baseUser = userUtil.userToBaseUser(user);
            baseUsers.add(baseUser);
        });

        return baseUsers;
    }
}
