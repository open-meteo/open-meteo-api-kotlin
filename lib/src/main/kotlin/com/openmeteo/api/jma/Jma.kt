package com.openmeteo.api.jma

import com.openmeteo.api.common.query.*
import com.openmeteo.api.common.response.*
import com.openmeteo.api.common.response.ResponseCurrentWeather.CurrentWeather
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Unit
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object Jma {

    val context = URL("https://api.open-meteo.com/v1/jma")

    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<JmaHourly>? = null,
        override val daily: Iterable<JmaDaily>? = null,
        override val currentWeather: Boolean? = null,
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
        QueryDaily,
        QueryCurrentWeather,
        QueryDateRange

    @Serializable
    open class Response(
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
        override val hourlyUnits: Map<JmaHourly, Unit> = emptyMap(),
        @SerialName("hourly")
        override val hourlyValues: Map<JmaHourly, Array<Double?>> = emptyMap(),
        @SerialName("daily_units")
        override val dailyUnits: Map<JmaDaily, Unit> = emptyMap(),
        @SerialName("daily")
        override val dailyValues: Map<JmaDaily, Array<Double?>> = emptyMap(),
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
        @SerialName("current_weather")
        override val currentWeather: CurrentWeather? = null,
    ) : ResponseCoordinates,
        ResponseHourly,
        ResponseDaily,
        ResponseCurrentWeather,
        ResponseGenerationTimed

}
