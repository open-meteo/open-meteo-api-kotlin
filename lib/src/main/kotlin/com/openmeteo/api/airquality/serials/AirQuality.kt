package com.openmeteo.api.airquality.serials

import com.openmeteo.api.common.serials.TimeZoneSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class AirQuality(
    val latitude: Float,
    val longitude: Float,

    @SerialName("generationtime_ms")
    val generationMilliseconds: Double,

    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,

    @Serializable(with = TimeZoneSerializer::class)
    @SerialName("timezone")
    val timeZone: TimeZone,

    @SerialName("timezone_abbreviation")
    val timeZoneAbbreviation: String,

    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits? = null,
    val hourly: Hourly? = null,
)