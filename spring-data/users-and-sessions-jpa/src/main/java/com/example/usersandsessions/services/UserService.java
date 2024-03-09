package com.example.usersandsessions.services;

import com.example.usersandsessions.dto.UserAndSession;
import com.example.usersandsessions.dto.UserInfo;
import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import com.example.usersandsessions.repositories.SessionRepository;
import com.example.usersandsessions.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final SessionRepository sessionRepository;
    private final UsersRepository usersRepository;

    public User getOrCreateUserByOID(String oid) {

        Optional<User> optionalUser = usersRepository.findFirstByOid(oid);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        try {

            User user = new User();
            user.setOid(oid);
            User newUser = usersRepository.save(user);
            return newUser;
        }
        catch (Exception e) {
            String message = String.format("Exception while save new user oid: %s ", oid);
            log.warn( message, e);
        }

        return null;
    }

    public UserAndSession getUserInfo(String userId) {
        UUID userIdAsUUID = UUID.fromString(userId);
        Optional<User> optionalUser = usersRepository.findById(userIdAsUUID);
        if (optionalUser.isEmpty()) {
            return new UserAndSession(null, null);
        }

        User user = optionalUser.get();
        Optional<Session> optionalSession = sessionRepository.findFirstByUserOrderByCreatedAtDesc(user);
        return new UserAndSession(user, optionalSession.orElse(null));
    }

}
