package com.group.hassocial.repository;

import com.group.hassocial.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT * FROM USERS u WHERE u.Email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE USERS u SET u.isVerified=true WHERE u.email=?1", nativeQuery = true)
    void verifyUser(String email);

    @Query(value = "SELECT * FROM USERS WHERE Gender = ?2 AND UserID not in (SELECT UserID FROM SWIPES WHERE TargetID = ?1) UNION SELECT * FROM USERS WHERE Gender = ?2 AND UserID in (SELECT UserID FROM SWIPES WHERE TargetID = ?1 AND IsAccepted = 1)", nativeQuery = true)
    Optional<List<User>> findMatches(Integer userID, Boolean gender);

    @Query(value = "SELECT * FROM USERS WHERE UserID not in (SELECT UserID FROM SWIPES WHERE TargetID = 1) UNION SELECT * FROM USERS WHERE UserID in (SELECT UserID FROM SWIPES WHERE TargetID = 1 AND IsAccepted = 1)SELECT * FROM USERS WHERE UserID not in (SELECT UserID FROM SWIPES WHERE TargetID = 1) UNION SELECT * FROM USERS WHERE UserID in (SELECT UserID FROM SWIPES WHERE TargetID = 1 AND IsAccepted = 1)", nativeQuery = true)
    Optional<List<User>> findMatchesFromBothGender(Integer userID);

    @Query(value = "SELECT TOP 1 USERS.Email\n" +
                   "FROM USERS INNER JOIN AUTHENTICATION_TOKEN ON USERS.UserID = AUTHENTICATION_TOKEN.UserID\n" +
                   "WHERE token IS NOT NULL AND confirmedAt IS NOT NULL AND\n" +
                   "expiresAt > DATEADD(hh, -24, GETDATE()) ORDER BY confirmedAt DESC", nativeQuery = true)
    String findAuthenticatedUserEmailByToken();

    @Transactional
    @Modifying
    @Query(value = "UPDATE USERS SET FullName = ?1, BirthDate = ?2, Gender = ?3, Orientation = ?4, Description = ?5 WHERE UserID = ?6", nativeQuery = true)
    void updateUser(String fullName, Date birthDate, Boolean gender, Integer orientation, String desciption, Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE USERS SET AvatarImageID = ?1 WHERE UserID = ?2", nativeQuery = true)
    void updateAvatar(Integer avatarImageId, Integer id);


}
