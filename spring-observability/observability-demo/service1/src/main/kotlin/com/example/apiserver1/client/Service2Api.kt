package com.example.apiserver1.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


data class Ping2Response(val message: String)
@FeignClient(name = "api-server-2", url = "http://localhost:8082")
interface Service2Api {

    @GetMapping("/ping2")
    fun ping2(): Ping2Response
}
