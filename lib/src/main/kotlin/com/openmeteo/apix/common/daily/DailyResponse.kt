package com.openmeteo.apix.common.daily

import com.openmeteo.apix.common.time.TimeZoneResponse
import kotlinx.serialization.SerialName

interface DailyResponse : TimeZoneResponse {
    @SerialName("hourly_units")
    val dailyUnits: DailyUnits
    @SerialName("hourly")
    val dailyValues: DailyValues
}
