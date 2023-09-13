package com.example.observabilitydemo.controller

import com.example.observabilitydemo.service.PingService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.observation.annotation.Observed
import org.slf4j.MDC
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val pingService: PingService) {
    private val log = KotlinLogging.logger { }

    @Observed
    @GetMapping("/ping")
    fun ping(): String {
//        val traceId = MDC.get("traceId")
//        var spanId = MDC.get("spanId")
        val contextMap = MDC.getCopyOfContextMap()
        log.info { "contextMap: $contextMap" }
        try {
            log.info { "ping" }
            return pingService.ping()
        } catch (e: Throwable) {
            e.printStackTrace()
            return "Oops"
        }
    }
}
