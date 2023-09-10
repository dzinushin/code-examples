package com.example.observabilitydemo.service

import com.example.observabilitydemo.clients.Service1Api
import com.example.observabilitydemo.clients.Service1ApiManual
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class PingService(private val service1Api: Service1Api, private val service1ApiManual: Service1ApiManual) {
    companion object {
        private val log = KotlinLogging.logger { }
    }
    fun ping(): String {
        log.info { "ping from DemoService" }
//        val response = apiServer1Client.ping1()
//        log.info { "response from api server 1: $response" }
        val response = service1ApiManual.ping1()
        return response.message
    }
}
