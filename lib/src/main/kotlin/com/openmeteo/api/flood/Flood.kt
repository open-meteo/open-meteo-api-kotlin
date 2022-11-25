package com.openmeteo.api.flood

import com.openmeteo.api.common.query.QueryCoordinates
import com.openmeteo.api.common.query.QueryDaily
import com.openmeteo.api.common.query.QueryDateRange
import com.openmeteo.api.common.response.*
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object Flood {

    val context = URL("https://flood-api.open-meteo.com/v1/flood")

    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val daily: Iterable<FloodDaily>? = null,
        override val startDate: Date? = null,
        override val endDate: Date? = null,
    ) : QueryCoordinates,
        QueryDaily,
        QueryDateRange

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        @SerialName("daily_units")
        override val dailyUnits: Map<FloodDaily, Unit> = emptyMap(),
        @SerialName("daily")
        override val dailyValues: Map<FloodDaily, Array<Double?>> = emptyMap(),
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseDaily,
        ResponseGenerationTimed

}
