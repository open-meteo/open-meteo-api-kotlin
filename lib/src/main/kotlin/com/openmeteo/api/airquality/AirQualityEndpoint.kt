package com.openmeteo.api.airquality

import com.openmeteo.api.airquality.params.*
import com.openmeteo.api.airquality.serials.AirQuality
import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.common.params.*
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.TimeZone
import java.net.URL

class AirQualityEndpoint(
    val latitude: Float = 52.5235f,
    val longitude: Float = 13.4115f,
    context: URL = URL("https://air-quality-api.open-meteo.com/v1/air-quality/")
) : Endpoint(context) {
    @ExperimentalSerializationApi
    operator fun invoke(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<Hourly>? = null,
        domains: Domains? = null,
        timeZone: TimeZone? = null,
        pastDays: Int? = null,
        startDate: IsoDate? = null,
        endDate: IsoDate? = null,
    ) = query<AirQuality>(
        "latitude" to latitude,
        "longitude" to longitude,
        "hourly" to hourly?.joinToString(","),
        "domains" to domains,
        "timeformat" to TimeFormat.unixtime,
        "timezone" to timeZone?.id,
        "past_days" to pastDays?.takeIf { it in 0..2 },
        "start_date" to startDate,
        "end_date" to endDate,
    )
}