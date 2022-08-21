package com.openmeteo.api.forecast.serials

import com.openmeteo.api.common.serials.Time
import kotlinx.serialization.Serializable

@Serializable
class CurrentWeather(
    val time: Time,
    val temperature: Float,
    val weathercode: WeatherCode,
    val windspeed: Float,
    val winddirection: Short,
)
