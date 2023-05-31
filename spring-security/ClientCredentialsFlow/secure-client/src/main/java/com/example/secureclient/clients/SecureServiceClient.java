package com.example.secureclient.clients;


import com.example.secureclient.configuration.FeignConfiguration;
import com.example.secureclient.configuration.SecureFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "secureServiceClient", url = "http://localhost:8080", configuration = SecureFeignConfiguration.class)
public interface SecureServiceClient {
    @GetMapping("/hello")
    String getHello(@RequestHeader("x-client-id") String clientId);
}
