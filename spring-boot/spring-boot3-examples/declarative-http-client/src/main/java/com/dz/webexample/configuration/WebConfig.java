package com.dz.webexample.configuration;

import com.dz.webexample.httpclients.GithubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {
    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .build();
    }

    @Bean
    GithubClient githubClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();

        return httpServiceProxyFactory.createClient(GithubClient.class);
    }
}
