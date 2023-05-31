package com.example.secureserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    //    @Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
//    String introspectionUri;
//
//    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
//    String clientId;
//
//    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
//    String clientSecret;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/public/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::opaqueToken)
//                .oauth2ResourceServer((oauth2) -> oauth2
//                        .opaqueToken((opaque) -> opaque
//                                .introspectionUri(this.introspectionUri)
//                                .introspectionClientCredentials(clientId, clientSecret)
//                        )
//                )
        ;
        return http.build();
    }

}
