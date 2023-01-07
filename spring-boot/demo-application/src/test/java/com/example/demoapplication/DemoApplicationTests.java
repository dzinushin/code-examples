package com.example.demoapplication;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@Slf4j
class DemoApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	protected int localServerPort;

	protected RequestSpecification requestSpecification;

	@Test
	void contextLoads() {
	}

	@Test
	void check_that_actuator_probes_works() {

		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/actuator/health/liveness", String.class);
		log.info("resp: {}", responseEntity);
		String body = responseEntity.getBody();
		log.info("body: {}", body);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void should_work_readiness_probe() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		requestSpecification = new RequestSpecBuilder()
				.setPort(localServerPort)
				.build();

		given(requestSpecification)
			.when()
			.get("/actuator/health")
			.then()
			.statusCode(200)
			.log().ifValidationFails(LogDetail.ALL);
	}
}
