package com.openmeteo.api.forecast

import com.openmeteo.api.common.TimeZone
import com.openmeteo.api.common.params.IsoDate
import com.openmeteo.api.forecast.params.Daily
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test

class ForecastEndpointTest {

    companion object {
        val endpoint = ForecastEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Empty query doesn't throw`() {
        endpoint().getOrThrow()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Weather codes of the upcoming days`() {
        val response = endpoint(
            daily = listOf(
                Daily.weathercode
            ), timeZone = TimeZone("Europe/Berlin")
        ).getOrThrow()
        val daily = response.daily!!
        val dailyTime = daily.time
        val dailyWeatherCode = daily.weathercode!!
        val dailyTimeZipWeatherCode = dailyTime.zip(dailyWeatherCode)
        for ((time, weatherCode) in dailyTimeZipWeatherCode)
            println("On ${IsoDate(time)} the weather is going to be: $weatherCode")
    }
}
