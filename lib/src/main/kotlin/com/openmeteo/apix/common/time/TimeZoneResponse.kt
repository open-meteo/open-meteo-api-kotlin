package com.openmeteo.apix.common.time

import kotlinx.serialization.SerialName

interface TimeZoneResponse {
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
    @SerialName("timezone")
    val timeZone: TimeZone
    @SerialName("timezone_abbreviation")
    val timeZoneAbbreviation: String
}
