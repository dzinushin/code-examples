package com.example.apiserver1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Service1Application

fun main(args: Array<String>) {
	runApplication<Service1Application>(*args)
}
