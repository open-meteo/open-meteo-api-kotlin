package com.openmeteo.apix.common.response

import com.openmeteo.apix.common.time.TimeZone
import kotlinx.serialization.SerialName

interface ResponseTimeZone : Response {
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
    @SerialName("timezone")
    val timeZone: TimeZone
    @SerialName("timezone_abbreviation")
    val timeZoneAbbreviation: String
}
