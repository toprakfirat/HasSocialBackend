package com.group.hassocial.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "SWIPES")
@Builder
@AllArgsConstructor
@Setter
@Getter
public class Swipe {

    private static final String DATE_PATTERN = "yyyy/MM/dd";

    @SneakyThrows
    public Swipe() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SwipeID", unique = true, nullable = false)
    private int SwipeID;

    @Column
    private int UserID;

    @Column
    private int TargetID;

    @Column
    private boolean IsAccepted;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column
    private Date Date;

    public static java.sql.Date datePatternOrganizer(String anyDate) throws ParseException {
        Date date = new SimpleDateFormat(DATE_PATTERN).parse(anyDate);
        return new java.sql.Date(date.getTime());
    }
}

