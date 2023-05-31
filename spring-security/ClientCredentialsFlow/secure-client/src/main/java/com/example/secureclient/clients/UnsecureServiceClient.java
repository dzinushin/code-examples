package com.example.secureclient.clients;

import com.example.secureclient.configuration.UnsecuredFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "unsecureServiceClient",  url = "http://localhost:8080/public", configuration = UnsecuredFeignConfiguration.class)
public interface UnsecureServiceClient {
    @GetMapping("/hello")
    String hello();
}
