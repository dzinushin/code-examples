package dev.dz.dockercomposedemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;

import static java.lang.StringTemplate.STR;

@SpringBootApplication
public class DockerComposeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerComposeDemoApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(JdbcConnectionDetails jdbcConnectionDetails) {
		return args -> {
			var details = STR."""
conn details:
	class: \{jdbcConnectionDetails.getClass().getName()}
	url: \{jdbcConnectionDetails.getJdbcUrl()}
	user: \{jdbcConnectionDetails.getUsername()}
	pwd: \{jdbcConnectionDetails.getPassword()}
	driver: \{jdbcConnectionDetails.getDriverClassName()}
""";
			System.out.println(details);
		};
	}
}
