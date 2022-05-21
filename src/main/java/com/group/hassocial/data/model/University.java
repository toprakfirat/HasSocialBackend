package com.group.hassocial.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UNIVERSITY")
@Builder
@AllArgsConstructor
@Setter
@Getter
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", unique = true, nullable = false)
    private int UniversityID;

    @Column
    private String UniversityName;

    @Column
    private String MailDomain;

    public University() {

    }
}
