package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
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

object Flood : Endpoint(
    URL("https://flood-api.open-meteo.com/v1/flood")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    @Deprecated(
        "Hardcoded Cities are deprecated: use the geocoding API instead!",
        ReplaceWith(
            """
                GeocodingSearch(...) { count = 1 }.getOrThrow().results[0]
                    .let { Flood(it.latitude, it.longitude, apikey, context, query) }
            """,
            "com.openmeteo.api.GeocodingSearch"
        ),
        DeprecationLevel.WARNING
    )
    inline operator fun invoke(
        city: City,
        context: URL = this.context,
        apikey: String? = null,
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
        @SerialName("past_days")
        override var pastDays: Int? = null,
        @SerialName("forecast_days")
        override var forecastDays: Int? = null,
        @SerialName("start_date")
        override var startDate: Date? = null,
        @SerialName("end_date")
        override var endDate: Date? = null,
        var ensemble: Boolean? = null,
        override var models: String? = null,
        override var cellSelection: CellSelection? = null,
        override var apikey: String? = null,
    ) : Q.Coordinate, Q.Daily, Q.TimeFormat, Q.PastDays, Q.ForecastDays,
        Q.DateRange, Q.CellSelection, Q.CommercialLicense, Q.Models

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
    ) : R.Coordinate, R.GenerationTimed, R.TimeZone, R.Daily

    object Models : Options.Models, Options.Listable<Models>() {
        const val seamlessV3="seamless_v3"
        const val forecastV3="forecast_v3"
        const val consolidatedV3="consolidated_v3"
        //const val seamlessV4="seamless_v4"
        //const val forecastV4="forecast_v4"
        //const val consolidatedV4="consolidated_v4"
    }

    @Serializable
    object Daily : Options.Daily, Options.Listable<Daily>() {
        const val riverDischarge="river_discharge"
        const val riverDischargeMean="river_discharge_mean"
        const val riverDischargeMedian="river_discharge_median"
        const val riverDischargeMax="river_discharge_max"
        const val riverDischargeMin="river_discharge_min"
        const val riverDischargeP25="river_discharge_p25"
        const val riverDischargeP75="river_discharge_p75"
    }

}
