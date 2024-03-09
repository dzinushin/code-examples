package com.example.usersandsessions.dto;

import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAndSession {
    private User user;
    private Session session;
}
