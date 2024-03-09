package com.example.usersandsessions.controllers;

import com.example.usersandsessions.dto.CreateSessionRequest;
import com.example.usersandsessions.dto.SessionInfo;
import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import com.example.usersandsessions.services.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/session")
    public ResponseEntity<Void> createSession(@RequestBody CreateSessionRequest req) {
        log.info("create session req: {}",req);
        sessionService.createSession(req.getSessionId(), req.getOid(), req.getToken());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/session")
    public ResponseEntity<SessionInfo> getSession(@RequestParam(value = "session-id", required = true) String sessionId) {
        log.info("get session info for id: {}", sessionId);
        Optional<Session> activeSession = sessionService.findAndTouchActiveSession(sessionId);
        if (activeSession.isPresent()) {
            Session session = activeSession.get();
            SessionInfo sessionInfo = new SessionInfo();
            User user = session.getUser();
            sessionInfo.setUserId(user.getId().toString());
            sessionInfo.setOid(user.getOid());

            return ResponseEntity.ok(sessionInfo);
        } else  {
            return ResponseEntity.notFound().build();
        }
    }
}
