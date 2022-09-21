package com.openmeteo.api.historical

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
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Unit
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object Historical {

    val context = URL("https://archive-api.open-meteo.com/v1/era5")

    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<HistoricalHourly>? = null,
        override val daily: Iterable<HistoricalDaily>? = null,
        val temperatureUnit: TemperatureUnit? = null,
        val windSpeedUnit: WindSpeedUnit? = null,
        val precipitationUnit: PrecipitationUnit? = null,
        override val timeZone: TimeZone? = null,
        override val startDate: Date? = null,
        override val endDate: Date? = null,
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
        override val hourlyUnits: Map<HistoricalHourly, Unit> = emptyMap(),
        @SerialName("hourly")
        override val hourlyValues: Map<HistoricalHourly, Array<Double?>> = emptyMap(),
        @SerialName("daily_units")
        override val dailyUnits: Map<HistoricalDaily, Unit> = emptyMap(),
        @SerialName("daily")
        override val dailyValues: Map<HistoricalDaily, Array<Double?>> = emptyMap(),
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseCoordinates,
        ResponseHourly,
        ResponseDaily,
        ResponseGenerationTimed

}
