package com.openmeteo.apix.common.hourly

import kotlinx.serialization.SerialName

interface HourlyResponse {
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits?
    @SerialName("hourly")
    val hourlyValues: HourlyValues?
}
