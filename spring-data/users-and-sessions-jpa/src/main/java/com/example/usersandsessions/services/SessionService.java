package com.example.usersandsessions.services;

import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import com.example.usersandsessions.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.example.usersandsessions.services.SessionExpirationChecker.MAX_SESSION_INACTIVE_PERIOD_IN_SECONDS;

@RequiredArgsConstructor
@Slf4j
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserService userService;
    private final SessionExpirationChecker sessionExpirationChecker;

    public void createSession(String sessionId, String oid, String token) {

        User user = userService.getOrCreateUserByOID(oid);

        UUID uuiSessionId = UUID.fromString(sessionId);
        Session newSession = new Session();
        newSession.setId(uuiSessionId);
        newSession.setToken(token);
        newSession.setUser(user);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireAt = now.plusMinutes(15);
        newSession.setExpiredAt(expireAt);
        newSession.setAccessedAt(now);

        sessionRepository.save(newSession);
    }

    public Optional<Session> findAndTouchActiveSession(String sessionId) {
        UUID sessionIdAsUUID = UUID.fromString(sessionId);
        Optional<Session> optSession = sessionRepository.findById(sessionIdAsUUID);
        if (optSession.isEmpty()) {
            return optSession;
        }
        Session session = optSession.get();

        // check that session is not expired
        if (sessionExpirationChecker.expired(session)) {
            return Optional.empty();
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newExpired = now.plusSeconds(MAX_SESSION_INACTIVE_PERIOD_IN_SECONDS);
        session.setAccessedAt(now);
        session.setExpiredAt(newExpired);

        Session savedSession = sessionRepository.save(session);

        return Optional.of(savedSession);
    }
}
