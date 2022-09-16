package com.openmeteo.apix.common.response

import com.openmeteo.api.forecast.response.WeatherCode
import com.openmeteo.apix.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface ResponseCurrentWeather : Response {
    @SerialName("current_weather")
    val currentWeather: CurrentWeather

    @Serializable
    open class CurrentWeather(
        val time: Time,
        val temperature: Float,
        @SerialName("windspeed")
        val windSpeed: Float,
        @SerialName("winddirection")
        val windDirection: Float,
        @SerialName("weathercode")
        val weatherCode: WeatherCode
    )
}
