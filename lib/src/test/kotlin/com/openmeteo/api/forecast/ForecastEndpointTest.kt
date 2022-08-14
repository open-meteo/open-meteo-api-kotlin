package com.openmeteo.api.forecast

import kotlin.test.*

class ForecastEndpointTest {

    companion object {
        val forecastEndpoint = ForecastEndpoint()
    }

    @Test
    fun `empty query is ok`() {
        forecastEndpoint().fold({
            val text = it.use { it.bufferedReader().readText() }
            println(text)
        }, {
            throw it
        })
    }
}
