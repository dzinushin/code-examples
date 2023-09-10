package com.example.observabilitydemo.configuration

import com.example.observabilitydemo.clients.Service1ApiManual
import feign.Client
import feign.Feign
import feign.Logger
import feign.codec.Decoder
import feign.codec.Encoder
import feign.micrometer.MeteredClient
import feign.micrometer.MicrometerCapability
import feign.micrometer.MicrometerObservationCapability
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.observation.ObservationRegistry
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(FeignClientsConfiguration::class)
class ClientConfiguration {

    @Bean
    fun feignClient(meterRegistry: MeterRegistry): Client {
        return MeteredClient(
            Client.Default(null, null),
            meterRegistry,
        )
    }

    @Bean
    fun micrometerObservationCapability(registry: ObservationRegistry?): MicrometerObservationCapability {
        return MicrometerObservationCapability(registry)
    }

    @Bean
    fun micrometerCapability(meterRegistry: MeterRegistry): MicrometerCapability {
        return MicrometerCapability(meterRegistry)
    }

    @Bean
    fun service1ApiClientManual(
        feignClient: Client,
        encoder: Encoder,
        decoder: Decoder,
        micrometerCapability: MicrometerCapability,
        micrometerObservationCapability: MicrometerObservationCapability
    ): Service1ApiManual =
        Feign.builder()
            .client(feignClient)
            .logLevel(Logger.Level.FULL)
            .encoder(encoder)
            .decoder(decoder)
            .addCapability(micrometerObservationCapability)
            .addCapability(micrometerCapability)
            .target(Service1ApiManual::class.java, "http://localhost:8081")
}
