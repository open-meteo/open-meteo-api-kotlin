package com.openmeteo.apix.airquality

import com.openmeteo.apix.common.http.Endpoint
import com.openmeteo.apix.common.query.*
import com.openmeteo.apix.common.response.ResponseCoordinates
import com.openmeteo.apix.common.response.ResponseGenerationTimed
import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.Date
import com.openmeteo.apix.common.time.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

class AirQuality(
    override val latitude: Float,
    override val longitude: Float,
    override val hourly: Iterable<HourlyOptions>? = null,
    val domains: Domains? = null,
    override val timeZone: TimeZone? = null,
    override val startDate: Date? = null,
    override val endDate: Date? = null,
    override val pastDays: Int? = null,
    context: URL = Companion.context,
) : Endpoint(context),
    QueryCoordinates,
    QueryHourly,
    QueryDateRange,
    QueryTimeFormat {

    companion object {
        val context = URL("https://air-quality-api.open-meteo.com/v1/air-quality")
    }

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
        override val hourlyUnits: HourlyUnits? = null,
        @SerialName("hourly")
        override val hourlyValues: HourlyValues? = null,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly,
        ResponseGenerationTimed

    operator fun invoke(query: Query? = null) = query<Response>(query)

}
