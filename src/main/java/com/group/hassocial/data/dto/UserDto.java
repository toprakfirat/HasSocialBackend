package com.group.hassocial.data.dto;

import com.sun.istack.NotNull;
import org.joda.time.DateTime;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class UserDto {
    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private byte passwordHash;
    private String matchingPassword;

    private int userId;

    private Date birthDate;

    private String avatarImageId;

    private boolean isVerified;

    private DateTime createDate;
}
