package com.openmeteo.api.common.response

import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName

interface ResponseDaily<E: Enum<E>> : Response {
    @SerialName("daily_units")
    val dailyUnits: Map<E, Unit>?

    @SerialName("daily")
    val dailyValues: Map<E, Array<Double?>>?
}
