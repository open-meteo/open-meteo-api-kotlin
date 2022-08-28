package com.openmeteo.apix.common.daily

import kotlinx.serialization.SerialName

interface DailyResponse {
    @SerialName("hourly_units")
    val dailyUnits: DailyUnits
    @SerialName("hourly")
    val dailyValues: DailyValues
}
