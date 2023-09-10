package com.example.apiserver2.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController


data class PingResponse(val message: String)
@RestController
class Controller {
    companion object {
        private val log = KotlinLogging.logger { }
    }
    @GetMapping("/ping2")
    fun ping2(@RequestHeader headers: MultiValueMap<String, String>): PingResponse {
        log.info { "ping1 called, headers: ${headers}" }
        return PingResponse(message = "pong")
    }
}
