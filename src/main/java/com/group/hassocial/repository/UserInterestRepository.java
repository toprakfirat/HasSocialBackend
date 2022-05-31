package com.group.hassocial.repository;

import com.group.hassocial.data.model.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {

    @Query("SELECT i FROM UserInterest i WHERE i.UserID = ?1")
    List<UserInterest> findByUserID(int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserInterest WHERE UserID = ?1 AND InterestID =?2")
    void deleteByUserIdAndInterestId(int userId, int interestId);
}
