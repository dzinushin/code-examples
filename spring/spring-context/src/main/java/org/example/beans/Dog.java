package org.example.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
    private String name;

    @PostConstruct
    void postConstruct() {
        this.setName("Friend");
    }

    @PreDestroy
    void postDestroy() {
        System.out.println("PreDestroy in Dog");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
