package com.openmeteo.api.common

import com.openmeteo.api.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 */
@Serializable
open class CurrentWeather(
    /**
     *
     */
    val time: Time,
    /**
     *
     */
    val temperature: Float,
    /**
     *
     */
    @SerialName("windspeed")
    val windSpeed: Float,
    /**
     *
     */
    @SerialName("winddirection")
    val windDirection: Float,
    /**
     *
     */
    @SerialName("weathercode")
    val weatherCode: WeatherCode,
    /**
     * Whether the time step has daylight
     */
    @Serializable(with = IntAsBoolean::class)
    val isDay: Boolean,
)
