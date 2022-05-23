package com.group.hassocial.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GALLERY")
@Builder
@AllArgsConstructor
@Setter
@Getter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageID", unique = true, nullable = false)
    private int ImageID;

    @Column
    private int UserID;

    @Column
    private boolean IsAvatarImage;

    @Column
    private String AvatarImage;

    @Column
    private String Image;

    public Photo() {

    }
}
