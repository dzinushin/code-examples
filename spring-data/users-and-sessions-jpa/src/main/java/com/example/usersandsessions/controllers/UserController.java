package com.example.usersandsessions.controllers;

import com.example.usersandsessions.dto.UserAndSession;
import com.example.usersandsessions.dto.UserInfo;
import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import com.example.usersandsessions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public UserInfo userInfo(@RequestParam(value = "user-id", required = true) String userId) {
        UserAndSession userAndSession = userService.getUserInfo(userId);

        User user = userAndSession.getUser();
        Session session = userAndSession.getSession();

        String oid = user != null ? user.getOid() : null;
        String sessionId = session != null ? session.getId().toString() : null;
        String token = session != null ? session.getToken() : null;

        return new UserInfo(oid, token, sessionId);
    }
}
