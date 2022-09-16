package com.openmeteo.apix.common.response

import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName

interface ResponseHourly<E: Enum<E>> : ResponseTimeZone {
    @SerialName("hourly_units")
    val hourlyUnits: Map<E, Unit>?

    @SerialName("hourly")
    val hourlyValues: Map<E, Array<Double?>>?
}
