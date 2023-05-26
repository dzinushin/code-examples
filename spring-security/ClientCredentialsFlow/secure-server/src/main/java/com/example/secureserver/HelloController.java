package com.example.secureserver;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return String.format("Hello %s\nattributes: %s", principal.getName(), principal.getAttributes().toString());
    }

    @GetMapping("/hello1")
    public String hello1(Principal principal) {
        return String.format("Hello %s", principal.getName());
    }

    @GetMapping("/hello2")
    public String hello2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("Hello %s", authentication.getName());
    }
}
