package com.group.hassocial.model;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;

    private String FullName;
    private String Email;
    private Date BirthDate;
    private String AvatarImageID;
    private boolean IsVerified;
    private DateTime CreateDate;
    private byte PasswordHash;
}
