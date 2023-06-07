package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
import com.openmeteo.api.common.Options
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object Marine : Endpoint(
    URL("https://marine-api.open-meteo.com/v1/marine")
) {

    operator fun invoke(query: Query) = query<Response, Query>(query)

    @Serializable
    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val daily: String? = null,
        override val hourly: String? = null,
        @SerialName("start_date")
        override val startDate: Date? = null,
        @SerialName("end_date")
        override val endDate: Date? = null,
        @SerialName("past_days")
        override val pastDays: Int? = null,
        override val timezone: Timezone? = null,
        override val cellSelection: CellSelection? = null,
    ) : Q.Coordinate, Q.Daily, Q.Hourly, Q.TimeFormat, Q.Timezone, Q.PastDays,
        Q.DateRange, Q.CellSelection

    @Serializable
    data class Response(
        override val latitude: Float,
        override val longitude: Float,
        override val utcOffsetSeconds: Int,
        override val timezone: Timezone,
        override val timezoneAbbreviation: String,
        override val generationtimeMs: Float,
        override val dailyUnits: Map<String, Unit> = mapOf(),
        @SerialName("daily")
        override val dailyValues: Map<String, Array<Double?>> = mapOf(),
        override val hourlyUnits: Map<String, Unit> = mapOf(),
        @SerialName("hourly")
        override val hourlyValues: Map<String, Array<Double?>> = mapOf(),
    ) : R.Coordinate, R.GenerationTimed, R.TimeZone, R.Daily, R.Hourly

    @Serializable
    object Hourly : Options.Hourly, Options.Listable<Hourly>() {
        const val waveHeight="wave_height"
        const val waveDirection="wave_direction"
        const val wavePeriod="wave_period"
        const val windWaveHeight="wind_wave_height"
        const val windWaveDirection="wind_wave_direction"
        const val windWavePeriod="wind_wave_period"
        const val windWavePeakPeriod="wind_wave_peak_period"
        const val swellWaveHeight="swell_wave_height"
        const val swellWaveDirection="swell_wave_direction"
        const val swellWavePeriod="swell_wave_period"
        const val swellWavePeakPeriod="swell_wave_peak_period"
    }

    @Serializable
    object Daily : Options.Daily {
        const val waveHeightMax="wave_height_max"
        const val waveDirectionDominant="wave_direction_dominant"
        const val wavePeriodMax="wave_period_max"
        const val windWaveHeightMax="wind_wave_height_max"
        const val windWaveDirectionDominant="wind_wave_direction_dominant"
        const val windWavePeriodMax="wind_wave_period_max"
        const val windWavePeakPeriodMax="wind_wave_peak_period_max"
        const val swellWaveHeightMax="swell_wave_height_max"
        const val swellWaveDirectionDominant="swell_wave_direction_dominant"
        const val swellWavePeriodMax="swell_wave_period_max"
        const val swellWavePeakPeriodMax="swell_wave_peak_period_max"
    }

}
