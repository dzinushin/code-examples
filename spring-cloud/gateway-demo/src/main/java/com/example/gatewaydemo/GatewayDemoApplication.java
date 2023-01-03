package com.example.gatewaydemo;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.time.Duration;


@SpringBootApplication
public class GatewayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayDemoApplication.class, args);
	}
	// Resilience4J default circuit breaker configuration
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}

	// Java route config example
	// Uppercase request body
	// echo "Hello" | curlie POST https://httpbin.org/post
	// echo "Hello" | curlie POST :8080/post
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("modify_request_body", r -> r.path("/post/**")
						.filters(f -> f.modifyRequestBody(
								String.class, Msg.class, MediaType.APPLICATION_JSON_VALUE,
								(exchange, s) -> Mono.just(new Msg(s.toUpperCase()))))
						.uri("https://httpbin.org"))
				.build();
	}

	static class Msg {
		String msg = "";

		public Msg() { }

		public Msg(String s) {
			this.msg = s;
		}

		public String getMsg() {
			return this.msg;
		}

		public void setMsg(String s) {
			this.msg = s;
		}
	}
}
