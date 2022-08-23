package com.openmeteo.api.airquality.response

import com.openmeteo.api.common.time.TimeZone
import kotlinx.serialization.Serializable

@Serializable
class AirQuality(
    val latitude: Float,
    val longitude: Float,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: TimeZone,
    val timezone_abbreviation: String,
    val hourly_units: HourlyUnits? = null,
    val hourly: Hourly? = null,
)
