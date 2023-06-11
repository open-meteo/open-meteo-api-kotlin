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

object Flood : Endpoint(
    URL("https://flood-api.open-meteo.com/v1/flood")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    @Serializable
    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val daily: String? = null,
        @SerialName("past_days")
        override val pastDays: Int? = null,
        @SerialName("forecast_days")
        override val forecastDays: Int? = null,
        @SerialName("start_date")
        override val startDate: Date? = null,
        @SerialName("end_date")
        override val endDate: Date? = null,
        val ensemble: Boolean? = null,
        override val models: String? = null,
        override val cellSelection: CellSelection? = null,
        override val apikey: String? = null,
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
        override val dailyUnits: Map<String, Unit> = mapOf(),
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
