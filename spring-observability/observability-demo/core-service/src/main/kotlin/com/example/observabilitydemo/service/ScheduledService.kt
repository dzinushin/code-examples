package com.example.observabilitydemo.service

import com.example.observabilitydemo.clients.Service1ApiManual
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.observation.annotation.Observed
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ScheduledService(private val service1ApiManual: Service1ApiManual) {

    private val log = KotlinLogging.logger {}

    @Observed
    @Scheduled(fixedDelay = 5000)
    fun periodicTask() {
        log.info { "\n\nperiodicTask called" }
        val response = service1ApiManual.ping1()
        log.info { "response from api service 1: $response" }
    }
}
