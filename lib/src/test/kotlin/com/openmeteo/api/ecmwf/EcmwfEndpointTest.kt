package com.openmeteo.api.ecmwf

import com.openmeteo.api.common.time.IsoDate
import com.openmeteo.api.ecmwf.params.Hourly
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertContentEquals

class EcmwfEndpointTest {

    companion object {
        val endpoint = EcmwfEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Empty query doesn't throw`() {
        endpoint().getOrThrow()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Wind speeds of 2022-08-01`() {
        val response = endpoint(
            hourly = listOf(Hourly.windspeed_10m),
            startDate = IsoDate("2022-08-01"),
            endDate = IsoDate("2022-08-01"),
        ).getOrThrow()
        val hourly = response.hourly!!
        val hourlyWindSpeed = hourly.windspeed_10m!!
        assertContentEquals(
            arrayOf(
                14.1f,
                10.5f,
                14.9f,
                15.1f,
                11.2f,
                14.4f,
                9.4f,
                6.1f,
            ), hourlyWindSpeed
        )
    }
}
