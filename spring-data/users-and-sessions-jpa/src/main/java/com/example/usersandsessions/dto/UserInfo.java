package com.example.usersandsessions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserInfo {
    private String oid;
    private String token;
    private String sessionId;
}
