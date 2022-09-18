package com.openmeteo.api.common.response

import com.openmeteo.api.common.query.QueryHourly
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName

interface ResponseHourly : ResponseTimeZone {
    @SerialName("hourly_units")
    val hourlyUnits: Map<out QueryHourly.Options, Unit>

    @SerialName("hourly")
    val hourlyValues: Map<out QueryHourly.Options, Array<Double?>>
}
