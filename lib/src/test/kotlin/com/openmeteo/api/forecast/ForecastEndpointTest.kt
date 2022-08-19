package com.openmeteo.api.forecast

import com.openmeteo.api.common.params.IsoDate
import kotlinx.serialization.ExperimentalSerializationApi
import com.openmeteo.api.forecast.params.*
import java.util.*
import kotlin.test.*

class ForecastEndpointTest {

    companion object {
        val forecastEndpoint = ForecastEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Empty query doesn't throw`() {
        forecastEndpoint().getOrThrow()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Weather codes of the upcoming days`() {
        val response = forecastEndpoint(daily = listOf(
            Daily.weathercode
        ), timeZone = TimeZone.getTimeZone("Europe/Berlin")).getOrThrow()
        val daily = response.daily!!
        val dailyTime = daily.time
        val dailyWeatherCode = daily.weathercode!!
        val dailyTimeZipWeatherCode = dailyTime.zip(dailyWeatherCode)
        for ((time, weatherCode) in dailyTimeZipWeatherCode)
            println("On ${IsoDate(time)} the weather is going to be: $weatherCode")
    }
}
