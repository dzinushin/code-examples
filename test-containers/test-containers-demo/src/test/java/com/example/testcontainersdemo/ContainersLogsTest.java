package com.example.testcontainersdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.startupcheck.OneShotStartupCheckStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.output.OutputFrame.OutputType.STDERR;
import static org.testcontainers.containers.output.OutputFrame.OutputType.STDOUT;

@Testcontainers
public class ContainersLogsTest {

    Logger log = LoggerFactory.getLogger(ContainersLogsTest.class);

    @Container
    GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("alpine:3.16"))
            .withEnv("GREETING","Hello world!")
            .withCommand("/bin/sh", "-c",
                    "echo -e \">>> Starting up with GREETING=$GREETING\" && " +
                    "echo -e \">>> Shut down...\" 1>&2")
            .withStartupCheckStrategy(new OneShotStartupCheckStrategy());

    @Test
    void get_ct_logs_as_string() {
        log.info("*** run test");
        String logs = container.getLogs();
        log.info("*** container logs: {}", logs);
        String stdoutLogs = container.getLogs(STDOUT);
        String stderrLogs = container.getLogs(STDERR);
        log.info("stdoutLogs: {}", stdoutLogs);
        log.info("stderrLogs: {}", stderrLogs);

        String greetingValue = container.getEnvMap().get("GREETING");
        Assertions.assertEquals("Hello world!", greetingValue);
    }

    @Test
    void get_ct_logs_as_stream() {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log).withSeparateOutputStreams();

        container.followOutput(logConsumer);
        String greetingValue = container.getEnvMap().get("GREETING");
        Assertions.assertEquals("Hello world!", greetingValue);
    }
}
