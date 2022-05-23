package com.group.hassocial.util;

import com.group.hassocial.data.model.User;
import com.hassocial.swaggergen.model.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    public BaseUser userToBaseUser(final User user) {
        final BaseUser baseUser = modelMapperUtil.modelMapper.map(user, BaseUser.class);
        return baseUser;
    }

    public User baseUserToUser(final BaseUser baseUser) {
        final User user = modelMapperUtil.modelMapper.map(baseUser, User.class);
        return user;
    }
}
