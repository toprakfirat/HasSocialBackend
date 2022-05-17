package com.group.hassocial.data.token;
import com.group.hassocial.data.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUTHENTICATION_TOKEN")
@Builder
@AllArgsConstructor
@Setter
@Getter

public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "UserID")
    private User user;

    public AuthenticationToken() {
    }
}
