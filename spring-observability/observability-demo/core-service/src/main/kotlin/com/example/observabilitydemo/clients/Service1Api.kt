package com.example.observabilitydemo.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

data class Ping1Response(val message: String)
@FeignClient(name = "api-service1", url = "http://localhost:8081")
interface Service1Api {

    @GetMapping("/ping1")
    fun ping1(): Ping1Response
}
