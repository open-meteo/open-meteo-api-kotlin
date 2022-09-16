package com.openmeteo.apix.common.response

import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName

interface ResponseDaily<E: Enum<E>> : Response {
    @SerialName("daily_units")
    val dailyUnits: Map<E, Unit>?

    @SerialName("daily")
    val dailyValues: Map<E, Array<Double?>>?
}
