package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
import com.openmeteo.api.common.Options
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.LengthUnit
import com.openmeteo.api.common.units.Units
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object Marine : Endpoint(
    URL("https://marine-api.open-meteo.com/v1/marine")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    inline operator fun invoke(
        city: City,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = this(city.latitude, city.longitude, apikey, context, query)

    inline operator fun invoke(
        latitude: Float,
        longitude: Float,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = Query(latitude, longitude, apikey = apikey).let {
        it.query()
        this(it, context)
    }

    @Serializable
    open class Query(
        override var latitude: Float,
        override var longitude: Float,
        override var daily: String? = null,
        override var hourly: String? = null,
        @SerialName("start_date")
        override var startDate: Date? = null,
        @SerialName("end_date")
        override var endDate: Date? = null,
        @SerialName("past_days")
        override var pastDays: Int? = null,
        override var timezone: Timezone? = null,
        override var cellSelection: CellSelection? = null,
        @SerialName("length_unit")
        override var lengthUnit: LengthUnit? = null,
        override var apikey: String? = null,
    ) : Q.Coordinate, Q.Daily, Q.Hourly, Q.TimeFormat, Q.Timezone, Q.PastDays,
        Q.DateRange, Q.CellSelection, Q.LengthUnit, Q.CommercialLicense

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        override val utcOffsetSeconds: Int,
        override val timezone: Timezone,
        override val timezoneAbbreviation: String,
        override val generationtimeMs: Float,
        override val dailyUnits: Map<String, Units> = mapOf(),
        @SerialName("daily")
        override val dailyValues: Map<String, Array<Double?>> = mapOf(),
        override val hourlyUnits: Map<String, Units> = mapOf(),
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
    object Daily : Options.Daily, Options.Listable<Daily>() {
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
