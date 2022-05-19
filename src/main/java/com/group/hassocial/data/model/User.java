package com.group.hassocial.data.model;
import lombok.*;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Builder
@AllArgsConstructor
@Setter
@Getter
public class User {

    private static final String DATE_PATTERN = "yyyy/MM/dd";

    public User() {
        super();
        this.IsVerified=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", unique = true, nullable = false)
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
    private int UniversityID;

    @Column
    private boolean Gender;

    public static java.sql.Date datePatternOrganizer(String anyDate) throws ParseException {
        Date date = new SimpleDateFormat(DATE_PATTERN).parse(anyDate);
        return new java.sql.Date(date.getTime());
    }
}
