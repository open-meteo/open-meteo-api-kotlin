package com.openmeteo.api.gfs

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

object Gfs {

    val context = URL("https://api.open-meteo.com/v1/gfs")

    class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: Iterable<GfsHourly>? = null,
        override val daily: Iterable<GfsDaily>? = null,
        override val currentWeather: Boolean? = null,
        val temperatureUnit: TemperatureUnit? = null,
        val windSpeedUnit: WindSpeedUnit? = null,
        val precipitationUnit: PrecipitationUnit? = null,
        override val timeZone: TimeZone? = null,
        override val startDate: Date? = null,
        override val endDate: Date? = null,
        @SerialName("past_days")
        val pastDays: Int? = null,
        @SerialName("forecast_days")
        val forecastDays: Int? = null,
    ) : QueryCoordinates,
        QueryHourly,
        QueryDaily,
        QueryCurrentWeather,
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
        override val hourlyUnits: Map<GfsHourly, Unit>? = null,
        @SerialName("hourly")
        override val hourlyValues: Map<GfsHourly, Array<Double?>>? = null,
        @SerialName("daily_units")
        override val dailyUnits: Map<GfsDaily, Unit>?,
        @SerialName("daily")
        override val dailyValues: Map<GfsDaily, Array<Double?>>?,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
        @SerialName("current_weather")
        override val currentWeather: CurrentWeather? = null,
    ) : ResponseCoordinates,
        ResponseHourly<GfsHourly>,
        ResponseDaily<GfsDaily>,
        ResponseCurrentWeather,
        ResponseGenerationTimed

}
