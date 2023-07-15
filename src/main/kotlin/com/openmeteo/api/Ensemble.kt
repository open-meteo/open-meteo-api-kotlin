package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
import com.openmeteo.api.common.Options
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Units
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object Ensemble : Endpoint(
    URL("https://ensemble-api.open-meteo.com/v1/ensemble")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    @Deprecated(
        "Hardcoded Cities are deprecated: use the geocoding API instead!",
        ReplaceWith(
            """
                GeocodingSearch(...) { count = 1 }.getOrThrow().results[0]
                    .let { Ensemble(it.latitude, it.longitude, apikey, context, query) }
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
    ) = Query(latitude, longitude, apikey).let {
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
        @SerialName("forecast_days")
        override var forecastDays: Int? = null,
        override var timezone: Timezone? = null,
        @SerialName("temperature_unit")
        override var temperatureUnit: TemperatureUnit? = null,
        @SerialName("windspeed_unit")
        override var windSpeedUnit: WindSpeedUnit? = null,
        @SerialName("precipitation_unit")
        override var precipitationUnit: PrecipitationUnit? = null,
        override var elevation: Float? = null,
        override var models: String? = null,
        override var cellSelection: CellSelection? = null,
        override var apikey: String? = null,
    ) : Q.Coordinate, Q.Elevation, Q.Hourly, Q.TemperatureUnit, Q.WindSpeedUnit,
        Q.PrecipitationUnit, Q.TimeFormat, Q.Timezone, Q.PastDays, Q.ForecastDays,
        Q.DateRange, Q.Models, Q.CellSelection, Q.CommercialLicense

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        override val utcOffsetSeconds: Int,
        override val timezone: Timezone,
        override val timezoneAbbreviation: String,
        override val generationtimeMs: Float,
        override val elevation: Float,
        override val hourlyUnits: Map<String, Units> = mapOf(),
        @SerialName("hourly")
        override val hourlyValues: Map<String, Array<Double?>> = mapOf(),
    ) : R.Coordinate, R.Elevation, R.GenerationTimed, R.TimeZone, R.Hourly

    @Serializable
    object Models : Options.Models, Options.Listable<Models>() {
        const val iconSeamless="icon_seamless"
        const val iconGlobal="icon_global"
        const val iconEu="icon_eu"
        const val iconD2="icon_d2"
        const val gfsSeamless="gfs_seamless"
        const val gfs025="gfs025"
        const val gfs05="gfs05"
        const val ecmwfIfs04="ecmwf_ifs04"
        const val gemGlobal="gem_global"
    }

    @Serializable
    object Hourly : Options.Hourly, Options.Listable<Hourly>() {
        const val temperature2m="temperature_2m"
        const val relativehumidity2m="relativehumidity_2m"
        const val dewpoint2m="dewpoint_2m"
        const val apparentTemperature="apparent_temperature"
        const val precipitation="precipitation"
        const val rain="rain"
        const val snowfall="snowfall"
        const val snowDepth="snow_depth"
        const val weathercode="weathercode"
        const val pressureMsl="pressure_msl"
        const val surfacePressure="surface_pressure"
        const val cloudcover="cloudcover"
        const val visibility="visibility"
        const val et0FaoEvapotranspiration="et0_fao_evapotranspiration"
        const val vaporPressureDeficit="vapor_pressure_deficit"
        const val windspeed10m="windspeed_10m"
        const val windspeed80m="windspeed_80m"
        const val windspeed120m="windspeed_120m"
        const val winddirection10m="winddirection_10m"
        const val winddirection80m="winddirection_80m"
        const val winddirection120m="winddirection_120m"
        const val windgusts10m="windgusts_10m"
        const val temperature80m="temperature_80m"
        const val temperature120m="temperature_120m"
        const val surfaceTemperature="surface_temperature"
        const val soilTemperature0To10cm="soil_temperature_0_to_10cm"
        const val soilTemperature10To40cm="soil_temperature_10_to_40cm"
        const val soilTemperature40To100cm="soil_temperature_40_to_100cm"
        const val soilTemperature100To200cm="soil_temperature_100_to_200cm"
        const val soilMoisture0To10cm="soil_moisture_0_to_10cm"
        const val soilMoisture10To40cm="soil_moisture_10_to_40cm"
        const val soilMoisture40To100cm="soil_moisture_40_to_100cm"
        const val soilMoisture100To200cm="soil_moisture_100_to_200cm"
        const val uvIndex="uv_index"
        const val uvIndexClearSky="uv_index_clear_sky"
        const val cape="cape"
        const val freezinglevelHeight="freezinglevel_height"
        const val shortwaveRadiation="shortwave_radiation"
        const val directRadiation="direct_radiation"
        const val diffuseRadiation="diffuse_radiation"
        const val directNormalIrradiance="direct_normal_irradiance"
        const val shortwaveRadiationInstant="shortwave_radiation_instant"
        const val directRadiationInstant="direct_radiation_instant"
        const val diffuseRadiationInstant="diffuse_radiation_instant"
        const val directNormalIrradianceInstant="direct_normal_irradiance_instant"
    }

}
