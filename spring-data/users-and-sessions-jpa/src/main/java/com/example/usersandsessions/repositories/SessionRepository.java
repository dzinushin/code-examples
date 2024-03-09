package com.example.usersandsessions.repositories;

import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends ListCrudRepository<Session, UUID> {
    Optional<Session> findFirstByUserOrderByCreatedAtDesc(User user);
}
