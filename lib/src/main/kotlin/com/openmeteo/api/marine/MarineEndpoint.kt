package com.openmeteo.api.marine

import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.common.params.IsoDate
import com.openmeteo.api.common.params.TimeFormat
import com.openmeteo.api.common.serials.BadRequest
import com.openmeteo.api.marine.params.Daily
import com.openmeteo.api.marine.params.Hourly
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL
import java.util.*

class MarineEndpoint(
    val latitude: Float = 54.3213f,
    val longitude: Float = 10.1348f,
    context: URL = URL("https://marine-api.open-meteo.com/v1/marine/")
) : Endpoint(context) {
    @ExperimentalSerializationApi
    operator fun invoke(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<Hourly>? = null,
        daily: Iterable<Daily>? = null,
        timeZone: TimeZone? = null,
        pastDays: Int? = null,
        startDate: IsoDate? = null,
        endDate: IsoDate? = null,
    ) = query<BadRequest>(
        "latitude" to latitude,
        "longitude" to longitude,
        "hourly" to hourly?.joinToString(","),
        "daily" to daily?.joinToString(","),
        "timeformat" to TimeFormat.unixtime,
        "timezone" to timeZone?.id,
        "past_days" to pastDays?.takeIf { it in 0..2 },
        "start_date" to startDate,
        "end_date" to endDate,
    )
}