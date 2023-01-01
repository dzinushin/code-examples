package com.dz.webexample.httpclients;

import com.dz.webexample.domain.GithubContributor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;

@HttpExchange(url = "/repos", accept = "application/json", contentType = "application/json")
public interface GithubClient {
    @GetExchange("/{user}/{project}")
    Flux<GithubContributor> contributors(@PathVariable("user") String user, @PathVariable("project") String project);
}
