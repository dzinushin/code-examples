package com.example.secureserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicHelloController {

    private Logger logger = LoggerFactory.getLogger(PublicHelloController.class);
    @GetMapping("/hello")
    public String hello() {
        logger.info("Request to unsecure /hello endpoint\n\n");
        return "Hello anonymous!!!";
    }
}
