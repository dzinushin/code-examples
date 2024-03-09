package com.example.usersandsessions.services;

import com.example.usersandsessions.entities.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionExpirationChecker {

    public static final int MAX_SESSION_INACTIVE_PERIOD_IN_SECONDS = 900; // 15 min
    public static final int MAX_SESSION_AGE = 180 * 60; // 3h

    public boolean expired(Session session) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createdAt = session.getCreatedAt();
        LocalDateTime expiredAt = session.getExpiredAt();
        LocalDateTime hardExpirationDT = createdAt.plusMinutes(MAX_SESSION_AGE);

        return expiredAt.isBefore(now) || hardExpirationDT.isBefore(now);
    }
}
