package com.openmeteo.api.airquality

import com.openmeteo.api.common.query.QueryCoordinates
import com.openmeteo.api.common.query.QueryDateRange
import com.openmeteo.api.common.query.QueryHourly
import com.openmeteo.api.common.response.ResponseCoordinates
import com.openmeteo.api.common.response.ResponseGenerationTimed
import com.openmeteo.api.common.response.ResponseHourly
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object AirQuality {

    val context = URL("https://air-quality-api.open-meteo.com/v1/air-quality")

    class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<AirQualityHourly>? = null,
        val domains: AirQualityDomains? = null,
        override val timeZone: TimeZone? = null,
        override val startDate: Date? = null,
        override val endDate: Date? = null,
        @SerialName("past_days")
        val pastDays: Int? = null,
    ) : QueryCoordinates,
        QueryHourly,
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
        override val hourlyUnits: Map<AirQualityHourly, Unit>? = null,
        @SerialName("hourly")
        override val hourlyValues: Map<AirQualityHourly, Array<Double?>>? = null,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly<AirQualityHourly>,
        ResponseGenerationTimed

}
