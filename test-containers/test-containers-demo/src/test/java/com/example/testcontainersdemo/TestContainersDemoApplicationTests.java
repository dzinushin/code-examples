package com.example.testcontainersdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@Slf4j
class TestContainersDemoApplicationTests {

	@Container
	static GenericContainer mockServerContainer = new GenericContainer(DockerImageName.parse("mockserver/mockserver"));

	public TestContainersDemoApplicationTests() {
		log.info("*** Ctor TestContainersDemoApplicationTests, class instance {}", this);
	}

	@BeforeAll
	public static void beforeAll() {
		log.info("** beforeAll ");
	}

	@AfterEach
	void afterEach() {
		log.info("**** after Each, class instance {}", this);
	}

	@AfterAll
	static void afterAll() {
		log.info("** afterAll ");
	}

	@BeforeEach
	public void beforeEach() {
		log.info("**** before Each, class instance: {} containerId: {} ", this, mockServerContainer.getContainerId());


	}

	@Test
	void test1() throws InterruptedException {
		log.info("**** run test1 , class instance {}", this);
	}

	@Test
	void test2() throws InterruptedException {
		log.info("**** run test2, class instance {}", this);
	}
}
