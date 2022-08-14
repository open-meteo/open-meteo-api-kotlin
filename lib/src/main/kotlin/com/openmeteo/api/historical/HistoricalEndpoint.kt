package com.openmeteo.api.historical

import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.common.params.*
import com.openmeteo.api.historical.params.*
import java.util.TimeZone
import java.net.URL

class HistoricalEndpoint(
    val latitude: Float = 52.5235f,
    val longitude: Float = 13.4115f,
    override val context: URL = URL("https://archive-api.open-meteo.com/v1/era5/")
) : Endpoint {
    operator fun invoke(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        startDate: IsoDate,
        endDate: IsoDate,
        hourly: Iterable<Hourly>? = null,
        daily: Iterable<Daily>? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeFormat: TimeFormat? = null,
        timeZone: TimeZone? = null,
    ) = query(
        "latitude" to latitude,
        "longitude" to longitude,
        "start_date" to startDate,
        "end_date" to endDate,
        "hourly" to hourly?.joinToString(","),
        "daily" to daily?.joinToString(","),
        "temperature_unit" to temperatureUnit,
        "windspeed_unit" to windSpeedUnit,
        "precipitation_unit" to precipitationUnit,
        "timeformat" to timeFormat,
        "timezone" to timeZone?.id,
    )
}