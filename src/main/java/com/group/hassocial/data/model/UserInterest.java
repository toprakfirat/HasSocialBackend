package com.group.hassocial.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INTERESTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int UserInterestsID;

    @Column
    private int UserID;

    @Column
    private int InterestID;
}
