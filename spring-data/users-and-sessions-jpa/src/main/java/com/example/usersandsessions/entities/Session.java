package com.example.usersandsessions.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    private User user;

    @Column(updatable = false)
    private String token;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    private LocalDateTime accessedAt;
}
