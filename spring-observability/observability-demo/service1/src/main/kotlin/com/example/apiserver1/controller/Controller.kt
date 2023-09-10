package com.example.apiserver1.controller

import com.example.apiserver1.service.Pong1Service
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController


data class PingResponse(val message: String)

@RestController
class Controller(private val pong1Service: Pong1Service) {

    companion object {
        private val log = KotlinLogging.logger { }
    }
    @GetMapping("/ping1")
    fun ping1(@RequestHeader headers:MultiValueMap<String, String>): PingResponse {
        log.info { "ping1 called, headers: ${headers}" }
        return PingResponse(message = pong1Service.pong1())
    }
}
