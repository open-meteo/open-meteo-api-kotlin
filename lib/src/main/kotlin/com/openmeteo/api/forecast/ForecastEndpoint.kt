package com.openmeteo.api.forecast

import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.common.params.*
import com.openmeteo.api.common.serials.BadRequest
import com.openmeteo.api.forecast.params.Daily
import com.openmeteo.api.forecast.params.Hourly
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL
import java.util.*

class ForecastEndpoint(
    val latitude: Float = 52.5235f,
    val longitude: Float = 13.4115f,
    context: URL = URL("https://api.open-meteo.com/v1/forecast/")
) : Endpoint(context) {
    @ExperimentalSerializationApi
    operator fun invoke(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<Hourly>? = null,
        daily: Iterable<Daily>? = null,
        currentWeather: Boolean? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        pastDays: Int? = null,
        startDate: IsoDate? = null,
        endDate: IsoDate? = null,
    ) = query<BadRequest>(
        "latitude" to latitude,
        "longitude" to longitude,
        "hourly" to hourly?.joinToString(","),
        "daily" to daily?.joinToString(","),
        "current_weather" to currentWeather,
        "temperature_unit" to temperatureUnit,
        "windspeed_unit" to windSpeedUnit,
        "precipitation_unit" to precipitationUnit,
        "timeformat" to TimeFormat.unixtime,
        "timezone" to timeZone?.id,
        "past_days" to pastDays?.takeIf { it in 0..2 },
        "start_date" to startDate,
        "end_date" to endDate,
    )
}