package com.group.hassocial.data.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.joda.time.DateTime;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class UserDto implements Serializable {
    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    private int userId;

    private Date birthDate;

    private String avatarImageId;

    private boolean isVerified;

    private DateTime createDate;

    private boolean Gender;
}
