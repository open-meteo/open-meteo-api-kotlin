package com.openmeteo.api

import com.openmeteo.api.common.Options
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.Units
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

    @Deprecated(
        "Hardcoded Cities are deprecated: use the geocoding API instead!",
        ReplaceWith(
            """
                GeocodingSearch.first(...).getOrNull()
                    ?.let { AirQuality(it.latitude, it.longitude, apikey, context, query) }
            """,
            "com.openmeteo.api.GeocodingSearch"
        ),
        DeprecationLevel.WARNING
    )
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
        override var hourly: String? = null,
        @SerialName("start_date")
        override var startDate: Date? = null,
        @SerialName("end_date")
        override var endDate: Date? = null,
        @SerialName("past_days")
        override var pastDays: Int? = null,
        override var timezone: Timezone? = null,
        var domains: String? = null,
        override var apikey: String? = null,
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
        override val hourlyUnits: Map<String, Units> = mapOf(),
        @SerialName("hourly")
        override val hourlyValues: Map<String, Array<Double?>> = mapOf(),
    ) : R.Coordinate, R.GenerationTimed, R.TimeZone, R.Hourly

    @Serializable
    object Domains {
        const val auto = "auto"

        /**
         * Alias for [auto]
         */
        const val combine = auto
        const val camsEurope = "cams_europe"
        const val camsGlobal = "cams_global"
    }

    @Serializable
    object Hourly : Options.Hourly, Options.Listable<Hourly>() {
        const val pm10 = "pm10"
        const val pm25 = "pm2_5"
        const val carbonMonoxide = "carbon_monoxide"
        const val nitrogenDioxide = "nitrogen_dioxide"
        const val sulphurDioxide = "sulphur_dioxide"
        const val ozone = "ozone"
        const val aerosolOpticalDepth = "aerosol_optical_depth"
        const val dust = "dust"
        const val uvIndex = "uv_index"
        const val uvIndexClearSky = "uv_index_clear_sky"
        const val ammonia = "ammonia"
        const val alderPollen = "alder_pollen"
        const val birchPollen = "birch_pollen"
        const val grassPollen = "grass_pollen"
        const val mugwortPollen = "mugwort_pollen"
        const val olivePollen = "olive_pollen"
        const val ragweedPollen = "ragweed_pollen"
        const val europeanAqi = "european_aqi"
        const val europeanAqiPm25 = "european_aqi_pm2_5"
        const val europeanAqiPm10 = "european_aqi_pm10"
        const val europeanAqiNo2 = "european_aqi_no2"
        const val europeanAqiO3 = "european_aqi_o3"
        const val europeanAqiSo2 = "european_aqi_so2"
        const val usAqi = "us_aqi"
        const val usAqiPm25 = "us_aqi_pm2_5"
        const val usAqiPm10 = "us_aqi_pm10"
        const val usAqiNo2 = "us_aqi_no2"
        const val usAqiCo = "us_aqi_co"
        const val usAqiO3 = "us_aqi_o3"
        const val usAqiSo2 = "us_aqi_so2"
    }

}
