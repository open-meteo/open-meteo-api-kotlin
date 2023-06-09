package com.openmeteo.api

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

object AirQuality : Endpoint(
    URL("https://air-quality-api.open-meteo.com/v1/air-quality")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    @Serializable
    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val hourly: String? = null,
        @SerialName("start_date")
        override val startDate: Date? = null,
        @SerialName("end_date")
        override val endDate: Date? = null,
        @SerialName("past_days")
        override val pastDays: Int? = null,
        override val timezone: Timezone? = null,
        val domains: String? = null,
        override val apikey: String?,
    ) : Q.Coordinate, Q.Hourly, Q.TimeFormat, Q.DateRange, Q.PastDays, Q.Timezone,
        Q.CommercialLicense

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        override val utcOffsetSeconds: Int,
        override val timezone: Timezone,
        override val timezoneAbbreviation: String,
        override val generationtimeMs: Float,
        override val hourlyUnits: Map<String, Unit> = mapOf(),
        @SerialName("hourly")
        override val hourlyValues: Map<String, Array<Double?>> = mapOf(),
    ) : R.Coordinate, R.GenerationTimed, R.TimeZone, R.Hourly

    @Serializable
    object Domains {
        const val auto="auto"
        /**
         * Alias for [auto]
         */
        const val combine=auto
        const val camsEurope="cams_europe"
        const val camsGlobal="cams_global"
    }

    @Serializable
    object Hourly : Options.Hourly, Options.Listable<Hourly>() {
        const val pm10="pm10"
        const val pm25="pm2_5"
        const val carbonMonoxide="carbon_monoxide"
        const val nitrogenDioxide="nitrogen_dioxide"
        const val sulphurDioxide="sulphur_dioxide"
        const val ozone="ozone"
        const val aerosolOpticalDepth="aerosol_optical_depth"
        const val dust="dust"
        const val uvIndex="uv_index"
        const val uvIndexClearSky="uv_index_clear_sky"
        const val ammonia="ammonia"
        const val alderPollen="alder_pollen"
        const val birchPollen="birch_pollen"
        const val grassPollen="grass_pollen"
        const val mugwortPollen="mugwort_pollen"
        const val olivePollen="olive_pollen"
        const val ragweedPollen="ragweed_pollen"
        const val europeanAqi="european_aqi"
        const val europeanAqiPm25="european_aqi_pm2_5"
        const val europeanAqiPm10="european_aqi_pm10"
        const val europeanAqiNo2="european_aqi_no2"
        const val europeanAqiO3="european_aqi_o3"
        const val europeanAqiSo2="european_aqi_so2"
        const val usAqi="us_aqi"
        const val usAqiPm25="us_aqi_pm2_5"
        const val usAqiPm10="us_aqi_pm10"
        const val usAqiNo2="us_aqi_no2"
        const val usAqiCo="us_aqi_co"
        const val usAqiO3="us_aqi_o3"
        const val usAqiSo2="us_aqi_so2"
    }

}
