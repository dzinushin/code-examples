package com.example.usersandsessions;

import com.example.usersandsessions.entities.Session;
import com.example.usersandsessions.entities.User;
import com.example.usersandsessions.repositories.SessionRepository;
import com.example.usersandsessions.repositories.UsersRepository;
import com.example.usersandsessions.services.SessionService;
import com.example.usersandsessions.services.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Testcontainers
@SpringBootTest
//@Transactional
class UsersAndSessionsApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(UsersAndSessionsApplicationTests.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserService userService;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> pgsqlContainer =  new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));


	@Test
	void contextLoads() {
	}

	@Test
	void save() {
		String oid = "oid";
		User createdUser = userService.getOrCreateUserByOID(oid);
		log.info("createdUser: {}", createdUser);
	}

	@Test
	void save_session() {

		String oid = "oid";
		User createdUser = userService.getOrCreateUserByOID(oid);
		log.info("createdUser: {}", createdUser);

		UUID sessionId = UUID.fromString("697dffc9-8ba5-410c-b677-227475a73530");
		Session session = new Session();
		session.setUser(createdUser);
		session.setId(sessionId);
		session.setToken("token");
		session.setCreatedAt(LocalDateTime.now());
		session.setExpiredAt(LocalDateTime.now());
		session.setAccessedAt(LocalDateTime.now());

		Session savedSession = sessionRepository.save(session);

		log.info("savedSession: {}", savedSession);

		savedSession.setExpiredAt(LocalDateTime.now().plusMinutes(15));
		Session updatedSession = sessionRepository.save(savedSession);
		log.info("updatedSession: {}", updatedSession);
	}

	@Test
	void unique_constraint_violation_exception() {
		String oid = "oid";

		User user1 = new User();
		user1.setOid(oid);
		User savedUser1 = usersRepository.save(user1);

		User user2 = new User();
		user2.setOid(oid);

		try {
			User savedUser2 = usersRepository.save(user2);
		} catch (DataIntegrityViolationException e) {
			log.error("exception text: {}", e.getMessage());
//			log.error("Exception: ", e);
		}


	}
}
