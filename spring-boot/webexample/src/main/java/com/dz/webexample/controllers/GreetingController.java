package com.dz.webexample.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping("/**")
    public String greet() {
        return "Hello!";
    }
}
