package com.example.testcontainersdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class ContainersTests {

    Logger log = LoggerFactory.getLogger(ContainersTests.class);

    RestTemplate restTemplate = new RestTemplate();

    @Container
    static GenericContainer container = new GenericContainer(DockerImageName.parse("kennethreitz/httpbin")).withExposedPorts(80);

    @Test
    void test_that_ct_is_running() {

        log.info("**** just run test");


        String url= "http://" + container.getHost() + ":" + container.getMappedPort(80);
        log.info("URL: {}", url);
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/uuid", String.class);

        log.info("response: {}", response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
