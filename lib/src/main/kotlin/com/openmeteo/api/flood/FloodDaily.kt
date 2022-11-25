package com.openmeteo.api.flood

import com.openmeteo.api.common.query.QueryDaily.Options
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class FloodDaily : Options {
    @SerialName("time")
    Time,
    @SerialName("river_discharge")
    RiverDischarge,
}
