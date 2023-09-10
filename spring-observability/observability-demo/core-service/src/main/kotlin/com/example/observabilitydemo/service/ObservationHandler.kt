package com.example.observabilitydemo.service

import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationHandler
import org.springframework.stereotype.Component


//@Component
class ObservationHandler : ObservationHandler<Observation.Context> {
    override fun onStart(context: Observation.Context) {
        log.info("Before running the observation for context [{}]", context.name)
    }

    override fun onStop(context: Observation.Context) {
        log.info("After running the observation for context [{}]", context.name)
    }


    companion object {
        private val log = KotlinLogging.logger { }
    }

    override fun supportsContext(context: Observation.Context): Boolean {
        return true
    }
}
