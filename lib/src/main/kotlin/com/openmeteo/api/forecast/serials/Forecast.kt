package com.openmeteo.api.forecast.serials

import com.openmeteo.api.common.serials.TimeZoneSerializer
import kotlinx.serialization.Serializable
import java.util.*

class Forecast(
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    @Serializable(with = TimeZoneSerializer::class)
    val timezone: TimeZone,
    val timezone_abbreviation: String,
    val hourly: Hourly? = null,
    val hourly_units: HourlyUnits? = null,
    val daily: Daily? = null,
    val daily_units: DailyUnits? = null,
    val current_weather: CurrentWeather? = null,
)