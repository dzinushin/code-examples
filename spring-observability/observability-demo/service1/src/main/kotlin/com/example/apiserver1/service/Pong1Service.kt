package com.example.apiserver1.service

import com.example.apiserver1.client.Service2Api
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class Pong1Service(private val service2Api: Service2Api) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    fun pong1(): String {
        log.info { "pong1 service called!" }
        val response = service2Api.ping2()
        return response.message
    }
}
