package com.group.hassocial.repository;

import com.group.hassocial.data.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

    @Query(value = "SELECT UniversityID FROM UNIVERSITY u WHERE u.MailDomain = ?1", nativeQuery = true)
    int getUniversityId(String mailDomain);
}
