package com.example.secureserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello(@RequestHeader("x-client-id") String xClientId, @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Request from {} with x-client-id: {}", principal.getName(), xClientId);
        OAuth2AccessToken oAuth2AccessToken = (OAuth2AccessToken) authentication.getCredentials();
        logger.info("Credentials: {}\n\n", oAuth2AccessToken.getTokenValue());

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
