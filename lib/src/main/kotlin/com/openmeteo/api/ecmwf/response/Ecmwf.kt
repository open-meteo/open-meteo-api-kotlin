package com.openmeteo.api.ecmwf.response

import com.openmeteo.api.common.time.TimeZone
import kotlinx.serialization.Serializable

@Serializable
class Ecmwf(
    val latitude: Float,
    val longitude: Float,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: TimeZone,
    val timezone_abbreviation: String,
    val hourly: Hourly? = null,
    val hourly_units: HourlyUnits? = null,
)
