package com.dz.examples.springdata;

import com.dz.examples.springdata.entities.TestEntity;
import com.dz.examples.springdata.repository.TestRepository;
import com.dz.examples.springdata.service.SomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class SpringDataDemoApplicationTests {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private SomeService service;


	@BeforeEach
	void setUp() {
		testRepository.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void test_single_save() {
		TestEntity e = new TestEntity("1", "value1");

		testRepository.save(e);

		testRepository.findAll().forEach(System.out::println);

	}

	@Test
	void test1() {
		try {
			service.saveMany1(12);
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		long count = testRepository.count();
		System.out.println(">>>>>>>>>>>>>>>> count: " + count);
		testRepository.findAll().forEach(System.out::println);
	}

	@Test
	void test2() {
		try {
			service.saveMany2(12);
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		long count = testRepository.count();
		System.out.println(">>>>>>>>>>>>>>>> count: " + count);
		testRepository.findAll().forEach(System.out::println);
	}

	@Test
	void test3() {
		try {
			service.saveMany3(12);
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		long count = testRepository.count();
		System.out.println(">>>>>>>>>>>>>>>> count: " + count);
		testRepository.findAll().forEach(System.out::println);
	}

	@Test
	void test4() {
		try {
			service.saveMany3(12);
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		long count = testRepository.count();
		System.out.println(">>>>>>>>>>>>>>>> count: " + count);
		testRepository.findAll().forEach(System.out::println);
	}
}
