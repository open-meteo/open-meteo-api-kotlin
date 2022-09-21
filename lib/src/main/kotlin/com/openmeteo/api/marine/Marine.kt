package com.openmeteo.api.marine

import com.openmeteo.api.common.query.QueryCoordinates
import com.openmeteo.api.common.query.QueryDaily
import com.openmeteo.api.common.query.QueryDateRange
import com.openmeteo.api.common.query.QueryHourly
import com.openmeteo.api.common.response.ResponseCoordinates
import com.openmeteo.api.common.response.ResponseDaily
import com.openmeteo.api.common.response.ResponseGenerationTimed
import com.openmeteo.api.common.response.ResponseHourly
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object Marine {

    val context = URL("https://marine-api.open-meteo.com/v1/marine")

    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<MarineHourly>? = null,
        override val daily: Iterable<MarineDaily>? = null,
        override val timeZone: TimeZone? = null,
        override val startDate: Date? = null,
        override val endDate: Date? = null,
        @SerialName("past_days")
        val pastDays: Int? = null,
    ) : QueryCoordinates,
        QueryHourly,
        QueryDaily,
        QueryDateRange

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        @SerialName("utc_offset_seconds")
        override val utcOffsetSeconds: Int,
        @SerialName("timezone")
        override val timeZone: TimeZone,
        @SerialName("timezone_abbreviation")
        override val timeZoneAbbreviation: String,
        @SerialName("hourly_units")
        override val hourlyUnits: Map<MarineHourly, Unit> = emptyMap(),
        @SerialName("hourly")
        override val hourlyValues: Map<MarineHourly, Array<Double?>> = emptyMap(),
        @SerialName("daily_units")
        override val dailyUnits: Map<MarineDaily, Unit> = emptyMap(),
        @SerialName("daily")
        override val dailyValues: Map<MarineDaily, Array<Double?>> = emptyMap(),
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly,
        ResponseDaily,
        ResponseGenerationTimed

}
