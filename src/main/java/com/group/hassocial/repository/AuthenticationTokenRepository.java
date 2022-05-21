package com.group.hassocial.repository;

import com.group.hassocial.data.token.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    @Query(value = "SELECT * FROM AUTHENTICATION_TOKEN u WHERE u.token = ?1", nativeQuery = true)
    Optional<AuthenticationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE AUTHENTICATION_TOKEN SET confirmedAt = ?2 WHERE token = ?1", nativeQuery = true)
    void updateAuthenticationTime(String token, LocalDateTime localDateTime);
}
