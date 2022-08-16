package com.openmeteo.api.ecmwf

import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.common.params.*
import com.openmeteo.api.common.serials.BadRequest
import com.openmeteo.api.ecmwf.params.Hourly
import com.openmeteo.api.ecmwf.serials.Ecmwf
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL

class EcmwfEndpoint(
    val latitude: Float = 52.5235f,
    val longitude: Float = 13.4115f,
    context: URL = URL("https://api.open-meteo.com/v1/ecmwf/")
) : Endpoint(context) {
    @ExperimentalSerializationApi
    operator fun invoke(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<Hourly>? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        pastDays: Int? = null,
        startDate: IsoDate? = null,
        endDate: IsoDate? = null,
    ) = query<Ecmwf>(
        "latitude" to latitude,
        "longitude" to longitude,
        "hourly" to hourly?.joinToString(","),
        "temperature_unit" to temperatureUnit,
        "windspeed_unit" to windSpeedUnit,
        "precipitation_unit" to precipitationUnit,
        "timeformat" to TimeFormat.unixtime,
        "past_days" to pastDays?.takeIf { it in 0..2 },
        "start_date" to startDate,
        "end_date" to endDate,
    )
}