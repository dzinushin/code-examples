package com.example.observabilitydemo.clients

import feign.Headers
import feign.RequestLine

interface Service1ApiManual {
    @RequestLine("GET /ping1")
    @Headers(
        "Accept: application/json"
    )
    fun ping1(): Ping1Response
}
