package com.openmeteo.api.common.response

import com.openmeteo.api.common.query.QueryDaily
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName

interface ResponseDaily : Response {
    @SerialName("daily_units")
    val dailyUnits: Map<out QueryDaily.Options, Unit>

    @SerialName("daily")
    val dailyValues: Map<out QueryDaily.Options, Array<Double?>>
}
