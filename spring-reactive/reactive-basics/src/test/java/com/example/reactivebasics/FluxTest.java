package com.example.reactivebasics;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FluxTest {
    @Test
    void simple_flux_example() {
        Flux<String> fluxColors = Flux.just("red", "green", "blue");
        fluxColors.subscribe(System.out::println);
    }

    @Test
    void simple_mono_example() {
        Mono<String> monoHello = Mono.just("Hello!");
        monoHello.subscribe(System.out::println);
    }

    @Test
    void flux_with_map() {
        Flux<String> fluxColors = Flux.just("red", "green", "blue");
        fluxColors.map(c -> c + "!").subscribe(System.out::println);
    }

    @Test
    void flux_on_error() {
        Flux<String> fluxCalc = Flux.just(-1, 0, 1)
                .map(i -> "10 / " + i + " = " + (10 / i));

        fluxCalc.subscribe(value -> System.out.println("Next: " + value),
                error -> System.err.println("Error: " + error));
    }

    @Test
    void flux_zip() {

        Flux<String> fluxFruits = Flux.just("apple", "pear", "plum");
        Flux<String> fluxColors = Flux.just("red", "green", "blue");
        Flux<Integer> fluxAmounts = Flux.just(10, 20, 30);
        Flux.zip(fluxFruits, fluxColors, fluxAmounts).subscribe(System.out::println);
    }

    @Test
    void flux_step_verifier() {
        Flux<String> fluxCalc = Flux.just(-1, 0, 1)
                .map(i -> "10 / " + i + " = " + (10 / i));

        StepVerifier.create(fluxCalc)
                .expectNextCount(1)
                .expectError(ArithmeticException.class)
                .verify();
    }

    @Test
    void flux_block_last() {
        Integer rslt = Flux.just(1, 2, 3).blockLast();
        System.out.println("result: "+ rslt);
    }

    @Test
    void schedulers() {
        Scheduler schedulerA = Schedulers.newParallel("Scheduler A");
        Scheduler schedulerB = Schedulers.newParallel("Scheduler B");
        Scheduler schedulerC = Schedulers.newParallel("Scheduler C");

        Flux.just(1)
                .map(i -> {
                    System.out.println("First map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerA)
                .map(i -> {
                    System.out.println("Second map: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(schedulerB)
                .map(i -> {
                    System.out.println("Third map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerC)
                .map(i -> {
                    System.out.println("Fourth map: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(schedulerA)
                .map(i -> {
                    System.out.println("Fifth map: " + Thread.currentThread().getName());
                    return i;
                })
                .blockLast();
    }

    @Test
    void back_pressure_example() {
        Flux.range(1,5)
                .subscribe(new Subscriber<>() {
                    private Subscription s;
                    int counter;

                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe");
                        this.s = s;
                        System.out.println("Requesting 2 emissions");
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer i) {
                        System.out.println("onNext " + i);
                        counter++;
                        if (counter % 2 == 0) {
                            System.out.println("Requesting 2 emissions");
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    @Test
    void cold_publisher_example() throws InterruptedException {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        Thread.sleep(2000);
        intervalFlux.subscribe(i -> System.out.printf("Subscriber A, value: %d%n", i));
        Thread.sleep(2000);
        intervalFlux.subscribe(i -> System.out.printf("Subscriber B, value: %d%n", i));
        Thread.sleep(3000);
    }

    @Test
    void hot_publisher_example() throws InterruptedException {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        ConnectableFlux<Long> intervalCF = intervalFlux.publish();
        intervalCF.connect();
        Thread.sleep(2000);
        intervalCF.subscribe(i -> System.out.printf("Subscriber A, value: %d%n", i));
        Thread.sleep(2000);
        intervalCF.subscribe(i -> System.out.printf("Subscriber B, value: %d%n", i));
        Thread.sleep(3000);
    }

    @Test
    void wrapping_blocking_call() throws InterruptedException {
        Mono<String> blockingWrapper = Mono.fromCallable(() -> new BlockingService().makeCall());
        blockingWrapper = blockingWrapper.subscribeOn(Schedulers.boundedElastic());
        blockingWrapper.subscribe(System.out::println);
        Thread.sleep(5000);
    }

    class BlockingService {
        String makeCall() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // ...
            }
            return "done!";
        }
    }

    @Test
    void using_context_example() {
        // ???
    }
}
