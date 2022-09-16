package com.openmeteo.apix.marine

import com.openmeteo.apix.common.query.*
import com.openmeteo.apix.common.response.ResponseCoordinates
import com.openmeteo.apix.common.response.ResponseDaily
import com.openmeteo.apix.common.response.ResponseGenerationTimed
import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.Date
import com.openmeteo.apix.common.time.TimeZone
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object Marine {

    val context = URL("https://marine-api.open-meteo.com/v1/marine")

    class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<Hourly>? = null,
        override val daily: Iterable<Daily>? = null,
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
        @SerialName("utc_offset_seconds")
        override val utcOffsetSeconds: Int,
        @SerialName("timezone")
        override val timeZone: TimeZone,
        @SerialName("timezone_abbreviation")
        override val timeZoneAbbreviation: String,
        @SerialName("hourly_units")
        override val hourlyUnits: Map<Hourly, Unit>? = null,
        @SerialName("hourly")
        override val hourlyValues: Map<Hourly, Array<Double?>>? = null,
        @SerialName("daily_units")
        override val dailyUnits: Map<Daily, Unit>? = null,
        @SerialName("daily")
        override val dailyValues: Map<Daily, Array<Double?>>? = null,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly<Hourly>,
        ResponseDaily<Daily>,
        ResponseGenerationTimed

}
