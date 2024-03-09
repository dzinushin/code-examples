package com.example.usersandsessions.dto;

import lombok.Data;

@Data
public class CreateSessionRequest {
    private String sessionId;
    private String oid;
    private String token;
}
