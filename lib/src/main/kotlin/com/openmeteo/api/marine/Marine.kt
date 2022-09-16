package com.openmeteo.api.marine

import com.openmeteo.api.common.query.*
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

    class Query(
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
    class Response(
        override val latitude: Float,
        override val longitude: Float,
        val elevation: Float,
        @SerialName("utc_offset_seconds")
        override val utcOffsetSeconds: Int,
        @SerialName("timezone")
        override val timeZone: TimeZone,
        @SerialName("timezone_abbreviation")
        override val timeZoneAbbreviation: String,
        @SerialName("hourly_units")
        override val hourlyUnits: Map<MarineHourly, Unit>? = null,
        @SerialName("hourly")
        override val hourlyValues: Map<MarineHourly, Array<Double?>>? = null,
        @SerialName("daily_units")
        override val dailyUnits: Map<MarineDaily, Unit>? = null,
        @SerialName("daily")
        override val dailyValues: Map<MarineDaily, Array<Double?>>? = null,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly<MarineHourly>,
        ResponseDaily<MarineDaily>,
        ResponseGenerationTimed

}