package com.group.hassocial.data.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int UserID;

    @Column
    private String FullName;

    @Column
    private String Email;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column
    private Date BirthDate;

    @Column
    private String AvatarImageID;

    @Column
    private boolean IsVerified;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column
    private Date CreateDate;

    @Column
    private String PasswordHash;

    @Column
    private String ActivationToken;
}
