package com.openmeteo.api.common.response

import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName

interface ResponseHourly<E: Enum<E>> : ResponseTimeZone {
    @SerialName("hourly_units")
    val hourlyUnits: Map<E, Unit>?

    @SerialName("hourly")
    val hourlyValues: Map<E, Array<Double?>>?
}
