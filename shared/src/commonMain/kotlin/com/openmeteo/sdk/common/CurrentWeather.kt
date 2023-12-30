package com.openmeteo.sdk.common

//import com.openmeteo.sdk.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The current weather status.
 */
@Serializable
open class CurrentWeather(
    /**
     * The "current" time step to which the data is referred to.
     * It usually differs from the Date/Time of "now".
     */
    //val time: Time,
    /**
     * The temperature.
     */
    val temperature: Float,
    /**
     * The average wind speed.
     */
    @SerialName("windspeed")
    val windSpeed: Float,
    /**
     * The wind direction.
     */
    @SerialName("winddirection")
    val windDirection: Float,
    /**
     * The WMO [WeatherCode].
     */
    @SerialName("weathercode")
    val weatherCode: WeatherCode,
    /**
     * Whether the time step has daylight or not.
     */
    @Serializable(with = IntAsBoolean::class)
    val isDay: Boolean,
)
