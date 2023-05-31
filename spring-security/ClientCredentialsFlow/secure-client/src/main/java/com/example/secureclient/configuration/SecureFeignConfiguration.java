package com.example.secureclient.configuration;

import feign.Logger;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class SecureFeignConfiguration {
    @Bean
    public OAuth2AccessTokenInterceptor oAuth2AccessTokenInterceptor(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        return new OAuth2AccessTokenInterceptor("demo-client", oAuth2AuthorizedClientManager);
    }
    @Bean
    public Logger.Level feignLoggingLevel() {
        return Logger.Level.HEADERS;
    }

}
