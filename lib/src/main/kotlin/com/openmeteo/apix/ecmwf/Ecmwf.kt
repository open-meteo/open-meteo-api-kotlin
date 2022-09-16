package com.openmeteo.apix.ecmwf

import com.openmeteo.apix.common.query.*
import com.openmeteo.apix.common.response.ResponseCoordinates
import com.openmeteo.apix.common.response.ResponseGenerationTimed
import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.Date
import com.openmeteo.apix.common.time.TimeZone
import com.openmeteo.apix.common.units.PrecipitationUnit
import com.openmeteo.apix.common.units.TemperatureUnit
import com.openmeteo.apix.common.units.Unit
import com.openmeteo.apix.common.units.WindSpeedUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object Ecmwf {

    val context = URL("https://api.open-meteo.com/v1/ecmwf")

    class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<Hourly>? = null,
        val temperatureUnit: TemperatureUnit? = null,
        val windSpeedUnit: WindSpeedUnit? = null,
        val precipitationUnit: PrecipitationUnit? = null,
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
        override val hourlyUnits: Map<Hourly, Unit>? = null,
        @SerialName("hourly")
        override val hourlyValues: Map<Hourly, Array<Double?>>? = null,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly<Hourly>,
        ResponseGenerationTimed

}
