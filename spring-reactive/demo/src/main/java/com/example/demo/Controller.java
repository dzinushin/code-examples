package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final SlowService slowService;

    @GetMapping("/fast/{value}")
    public Mono<String> fastGet(@PathVariable("value") long value) {
        return Mono.fromCallable( () -> String.format("fast %d", value));
    }

    @GetMapping("/slow/{value}")
    public Mono<String> slowGet(@PathVariable("value") long value) {
        return Mono.fromCallable( () -> slowService.getString(value));
    }
}
