package com.example.observabilitydemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
class CoreServiceApplication()

fun main(args: Array<String>) {
	val context = runApplication<CoreServiceApplication>(*args)
}
