package com.openmeteo.api.forecast.response

import com.openmeteo.api.common.time.Time
import kotlinx.serialization.Serializable

@Serializable
class CurrentWeather(
    val time: Time,
    val temperature: Float,
    val weathercode: WeatherCode,
    val windspeed: Float,
    val winddirection: Short,
)
