package com.group.hassocial.model.dto;

import com.sun.istack.NotNull;
import org.joda.time.DateTime;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class UserDto {

    private int userId;
    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private byte passwordHash;

    private Date birthDate;
    private String avatarImageId;
    private boolean isVerified;
    private DateTime createDate;
}
