package com.openmeteo.api.ecmwf.serials

import com.openmeteo.api.common.serials.TimeZoneSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Ecmwf(
    val latitude: Float,
    val longitude: Float,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    @Serializable(with = TimeZoneSerializer::class)
    val timezone: TimeZone,
    val timezone_abbreviation: String,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
)