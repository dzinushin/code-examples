package com.dz.webexample.controllers;

import com.dz.webexample.domain.GithubContributor;
import com.dz.webexample.httpclients.GithubClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class Controller {
    private final GithubClient githubClient;

    public Controller(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @RequestMapping("/greet")
    public String greet() {
        return "Hello!";
    }

    @GetMapping("/contributors")
    public Flux<GithubContributor> contributors() {
        Flux<GithubContributor> contributors = githubClient.contributors("dzinushin", "dotfiles");
        return contributors;
    }
}
