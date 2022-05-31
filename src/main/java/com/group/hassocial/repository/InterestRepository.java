package com.group.hassocial.repository;

import com.group.hassocial.data.model.Interest;
import com.group.hassocial.data.model.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    @Query("SELECT i FROM Interest i WHERE i.InterestName = ?1")
    List<UserInterest> findByInterestName(String interestName);

}
