


https://github.com/jonatan-ivanov/teahouse

https://spring.io/blog/2022/10/12/observability-with-spring-boot-3


Для работы трейсинга должны присутсвовать 3 зависимости:

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-tracing")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
