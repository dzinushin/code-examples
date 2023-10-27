package org.example.configuration;

import org.example.beans.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org.example.beans")
public class ProjectConfig {

    @Bean()
    @Primary
    Parrot parrot1() {
        return new Parrot("Koko");
    }

    @Bean(name="riki")
    Parrot parrot2() {
        return new Parrot("Riki");
    }
}
