package com.openmeteo.api.forecast

import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.*

class ForecastEndpointTest {

    companion object {
        val forecastEndpoint = ForecastEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `empty query returns 200`() {
        //val response = forecastEndpoint().getOrThrow()
        //println(response)
    }
}
