package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
import com.openmeteo.api.common.CurrentWeather
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

object Historical : Endpoint(
    URL("https://archive-api.open-meteo.com/v1/archive")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    inline operator fun invoke(
        city: City,
        startDate: Date,
        endDate: Date,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = this(city.latitude, city.longitude, startDate, endDate, apikey, context, query)

    inline operator fun invoke(
        latitude: Float,
        longitude: Float,
        startDate: Date,
        endDate: Date,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = Query(latitude, longitude, startDate, endDate, apikey = apikey).let {
        it.query()
        this(it, context)
    }

    @Serializable
    open class Query(
        override var latitude: Float,
        override var longitude: Float,
        @SerialName("start_date")
        override var startDate: Date,
        @SerialName("end_date")
        override var endDate: Date,
        override var daily: String? = null,
        override var hourly: String? = null,
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
    ) : Q.Coordinate, Q.Elevation, Q.DateRange, Q.Daily, Q.Hourly, Q.TimeFormat, Q.Timezone,
        Q.TemperatureUnit, Q.WindSpeedUnit, Q.PrecipitationUnit, Q.Models, Q.CellSelection,
        Q.CommercialLicense

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        override val utcOffsetSeconds: Int,
        override val timezone: Timezone,
        override val timezoneAbbreviation: String,
        override val generationtimeMs: Float,
        override val elevation: Float,
        override val dailyUnits: Map<String, Units> = mapOf(),
        @SerialName("daily")
        override val dailyValues: Map<String, Array<Double?>> = mapOf(),
        override val hourlyUnits: Map<String, Units> = mapOf(),
        @SerialName("hourly")
        override val hourlyValues: Map<String, Array<Double?>> = mapOf(),
        override val currentWeather: CurrentWeather? = null,
    ) : R.Coordinate, R.Elevation, R.GenerationTimed, R.TimeZone, R.Daily, R.Hourly, R.CurrentWeather

    @Serializable
    object Models : Options.Models, Options.Listable<Models>() {
        const val bestMatch="best_match"
        const val era5="era5"
        const val era5Land="era5_land"
        const val cerra="cerra"
        const val ecmwfIfs="ecmwf_ifs"
    }

    @Serializable
    object Daily : Options.Daily, Options.Listable<Daily>() {
        const val weathercode="weathercode"
        const val temperature2mMax="temperature_2m_max"
        const val temperature2mMin="temperature_2m_min"
        const val temperature2mMean="temperature_2m_mean"
        const val apparentTemperatureMax="apparent_temperature_max"
        const val apparentTemperatureMin="apparent_temperature_min"
        const val apparentTemperatureMean="apparent_temperature_mean"
        const val sunrise="sunrise"
        const val sunset="sunset"
        const val shortwaveRadiationSum="shortwave_radiation_sum"
        const val precipitationSum="precipitation_sum"
        const val rainSum="rain_sum"
        const val snowfallSum="snowfall_sum"
        const val precipitationHours="precipitation_hours"
        const val windspeed10mMax="windspeed_10m_max"
        const val windgusts10mMax="windgusts_10m_max"
        const val winddirection10mDominant="winddirection_10m_dominant"
        const val et0FaoEvapotranspiration="et0_fao_evapotranspiration"
        const val soilTemperature0To100cmMean="soil_temperature_0_to_100cm_mean"
        const val soilMoisture0To100cmMean="soil_moisture_0_to_100cm_mean"
        const val soilMoistureIndex0To100cmMean="soil_moisture_index_0_to_100cm_mean"
        const val growingDegreeDaysBase0Limit50="growing_degree_days_base_0_limit_50"
        const val leafWetnessProbabilityMean="leaf_wetness_probability_mean"
        const val vaporPressureDeficitMax="vapor_pressure_deficit_max"
    }

    @Serializable
    object Hourly : Options.Hourly, Options.Listable<Hourly>() {
        const val temperature2m="temperature_2m"
        const val relativehumidity2m="relativehumidity_2m"
        const val dewpoint2m="dewpoint_2m"
        const val apparentTemperature="apparent_temperature"
        const val pressureMsl="pressure_msl"
        const val surfacePressure="surface_pressure"
        const val precipitation="precipitation"
        const val rain="rain"
        const val snowfall="snowfall"
        const val weathercode="weathercode"
        const val cloudcover="cloudcover"
        const val cloudcoverLow="cloudcover_low"
        const val cloudcoverMid="cloudcover_mid"
        const val cloudcoverHigh="cloudcover_high"
        const val shortwaveRadiation="shortwave_radiation"
        const val directRadiation="direct_radiation"
        const val diffuseRadiation="diffuse_radiation"
        const val directNormalIrradiance="direct_normal_irradiance"
        const val windspeed10m="windspeed_10m"
        const val windspeed100m="windspeed_100m"
        const val winddirection10m="winddirection_10m"
        const val winddirection100m="winddirection_100m"
        const val windgusts10m="windgusts_10m"
        const val et0FaoEvapotranspiration="et0_fao_evapotranspiration"
        const val vaporPressureDeficit="vapor_pressure_deficit"
        const val soilTemperature0To7cm="soil_temperature_0_to_7cm"
        const val soilTemperature7To28cm="soil_temperature_7_to_28cm"
        const val soilTemperature28To100cm="soil_temperature_28_to_100cm"
        const val soilTemperature100To255cm="soil_temperature_100_to_255cm"
        const val soilTemperature0To100cm="soil_temperature_0_to_100cm"
        const val soilMoisture0To7cm="soil_moisture_0_to_7cm"
        const val soilMoisture7To28cm="soil_moisture_7_to_28cm"
        const val soilMoisture28To100cm="soil_moisture_28_to_100cm"
        const val soilMoisture100To255cm="soil_moisture_100_to_255cm"
        const val soilMoisture0To100cm="soil_moisture_0_to_100cm"
        const val soilMoistureIndex0To7cm="soil_moisture_index_0_to_7cm"
        const val soilMoistureIndex7To28cm="soil_moisture_index_7_to_28cm"
        const val soilMoistureIndex28To100cm="soil_moisture_index_28_to_100cm"
        const val soilMoistureIndex100To255cm="soil_moisture_index_100_to_255cm"
        const val soilMoistureIndex0To100cm="soil_moisture_index_0_to_100cm"
    }


}
