package com.openmeteo.api.historical.serials

import com.openmeteo.api.common.serials.TimeZoneSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Historical(
    val latitude: Float,
    val longitude: Float,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    @Serializable(with = TimeZoneSerializer::class)
    val timezone: TimeZone,
    val timezone_abbreviation: String,
    val hourly: Hourly? = null,
    val hourly_units: HourlyUnits? = null,
    val daily: Daily? = null,
    val daily_units: DailyUnits? = null,
)
