package com.example.gatewaydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class GatewayFallback {

    @GetMapping("/drip")
    public String getDefaultUuid() {
        return "no drips that time...";
    }

}
