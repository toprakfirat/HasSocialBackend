package com.group.hassocial.repository;

import com.group.hassocial.data.model.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface SwipeRepository extends JpaRepository<Swipe, Integer> {

    @Query(value = "SELECT IsAccepted FROM SWIPES WHERE TargetID = ?1 AND UserID= ?2", nativeQuery = true)
    boolean checkPreviousMatch(Integer id, Integer swipedId);
}
