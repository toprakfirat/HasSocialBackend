package com.group.hassocial.repository;

import com.group.hassocial.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT * FROM USERS u WHERE u.Email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE USERS u SET u.isVerified=true WHERE u.email=:email", nativeQuery = true)
    void verifyUser(@Param("email") String email);

}
