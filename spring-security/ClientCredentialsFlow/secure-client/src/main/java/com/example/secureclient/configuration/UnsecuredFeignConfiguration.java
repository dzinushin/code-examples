package com.example.secureclient.configuration;

import feign.Logger;
import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnsecuredFeignConfiguration {

    @Bean
    public Logger.Level feignLoggingLevel1() {
        return Logger.Level.HEADERS;
    }
}
