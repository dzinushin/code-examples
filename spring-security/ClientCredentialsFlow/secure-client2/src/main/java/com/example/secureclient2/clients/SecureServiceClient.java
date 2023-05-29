package com.example.secureclient2.clients;

import com.example.secureclient2.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ServiceClient", url = "http://localhost:8080" , configuration = FeignConfiguration.class)
public interface SecureServiceClient {
    @GetMapping("/hello")
    String getHello(@RequestHeader("x-client-id") String clientId);
}
