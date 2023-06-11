package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
import com.openmeteo.api.common.CurrentWeather
import com.openmeteo.api.common.Options
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Unit
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object Forecast : Endpoint(
    URL("https://api.open-meteo.com/v1/forecast")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

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
        @SerialName("forecast_days")
        override val forecastDays: Int? = null,
        @SerialName("current_weather")
        override val currentWeather: Boolean? = null,
        override val timezone: Timezone? = null,
        @SerialName("temperature_unit")
        override val temperatureUnit: TemperatureUnit? = null,
        @SerialName("windspeed_unit")
        override val windSpeedUnit: WindSpeedUnit? = null,
        @SerialName("precipitation_unit")
        override val precipitationUnit: PrecipitationUnit? = null,
        override val elevation: Float? = null,
        override val models: String? = null,
        override val cellSelection: CellSelection? = null,
        override val apikey: String? = null,
    ) : Q.Coordinate, Q.Elevation, Q.Daily, Q.Hourly, Q.TimeFormat, Q.DateRange,
        Q.PastDays, Q.ForecastDays, Q.CurrentWeather, Q.Timezone, Q.Models,
        Q.TemperatureUnit, Q.WindSpeedUnit, Q.PrecipitationUnit, Q.CellSelection,
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
        override val dailyUnits: Map<String, Unit> = mapOf(),
        @SerialName("daily")
        override val dailyValues: Map<String, Array<Double?>> = mapOf(),
        override val hourlyUnits: Map<String, Unit> = mapOf(),
        @SerialName("hourly")
        override val hourlyValues: Map<String, Array<Double?>> = mapOf(),
        override val currentWeather: CurrentWeather? = null,
    ) : R.Coordinate, R.Elevation, R.GenerationTimed, R.TimeZone, R.Daily, R.Hourly, R.CurrentWeather

    @Serializable
    object Models : Options.Models, Options.Listable<Models>() {
        const val bestMatch="best_match"
        const val ecmwfIfs04="ecmwf_ifs04"
        const val metnoNordic="metno_nordic"
        const val gfsSeamless="gfs_seamless"
        const val gfsGlobal="gfs_global"
        const val gfsHrrr="gfs_hrrr"
        const val jmaSeamless="jma_seamless"
        const val jmaMsm="jma_msm"
        const val jmaGsm="jma_gsm"
        const val iconSeamless="icon_seamless"
        const val iconGlobal="icon_global"
        const val iconEu="icon_eu"
        const val iconD2="icon_d2"
        const val gemSeamless="gem_seamless"
        const val gemGlobal="gem_global"
        const val gemRegional="gem_regional"
        const val gemHrdpsContinental="gem_hrdps_continental"
        const val meteofranceSeamless="meteofrance_seamless"
        const val meteofranceArpegeWorld="meteofrance_arpege_world"
        const val meteofranceArpegeEurope="meteofrance_arpege_europe"
        const val meteofranceAromeFrance="meteofrance_arome_france"
        const val meteofranceAromeFranceHd="meteofrance_arome_france_hd"
    }

    @Serializable
    object Daily : Options.Daily, Options.Listable<Daily>() {
        const val weathercode="weathercode"
        const val temperature2mMax="temperature_2m_max"
        const val temperature2mMin="temperature_2m_min"
        const val apparentTemperatureMax="apparent_temperature_max"
        const val apparentTemperatureMin="apparent_temperature_min"
        const val sunrise="sunrise"
        const val sunset="sunset"
        const val uvIndexMax="uv_index_max"
        const val uvIndexClearSkyMax="uv_index_clear_sky_max"
        const val precipitationSum="precipitation_sum"
        const val rainSum="rain_sum"
        const val showersSum="showers_sum"
        const val snowfallSum="snowfall_sum"
        const val precipitationHours="precipitation_hours"
        const val precipitationProbabilityMax="precipitation_probability_max"
        const val windspeed10mMax="windspeed_10m_max"
        const val windgusts10mMax="windgusts_10m_max"
        const val winddirection10mDominant="winddirection_10m_dominant"
        const val shortwaveRadiationSum="shortwave_radiation_sum"
        const val et0FaoEvapotranspiration="et0_fao_evapotranspiration"
    }

    @Serializable
    object Hourly : Options.Hourly, Options.Listable<Hourly>() {
        const val temperature2m="temperature_2m"
        const val relativehumidity2m="relativehumidity_2m"
        const val dewpoint2m="dewpoint_2m"
        const val apparentTemperature="apparent_temperature"
        const val precipitationProbability="precipitation_probability"
        const val precipitation="precipitation"
        const val rain="rain"
        const val showers="showers"
        const val snowfall="snowfall"
        const val snowDepth="snow_depth"
        const val weathercode="weathercode"
        const val pressureMsl="pressure_msl"
        const val surfacePressure="surface_pressure"
        const val cloudcover="cloudcover"
        const val cloudcoverLow="cloudcover_low"
        const val cloudcoverMid="cloudcover_mid"
        const val cloudcoverHigh="cloudcover_high"
        const val visibility="visibility"
        const val evapotranspiration="evapotranspiration"
        const val et0FaoEvapotranspiration="et0_fao_evapotranspiration"
        const val vaporPressureDeficit="vapor_pressure_deficit"
        const val windspeed10m="windspeed_10m"
        const val windspeed80m="windspeed_80m"
        const val windspeed120m="windspeed_120m"
        const val windspeed180m="windspeed_180m"
        const val winddirection10m="winddirection_10m"
        const val winddirection80m="winddirection_80m"
        const val winddirection120m="winddirection_120m"
        const val winddirection180m="winddirection_180m"
        const val windgusts10m="windgusts_10m"
        const val temperature80m="temperature_80m"
        const val temperature120m="temperature_120m"
        const val temperature180m="temperature_180m"
        const val soilTemperature0cm="soil_temperature_0cm"
        const val soilTemperature6cm="soil_temperature_6cm"
        const val soilTemperature18cm="soil_temperature_18cm"
        const val soilTemperature54cm="soil_temperature_54cm"
        const val soilMoisture01cm="soil_moisture_0_1cm"
        const val soilMoisture13cm="soil_moisture_1_3cm"
        const val soilMoisture39cm="soil_moisture_3_9cm"
        const val soilMoisture927cm="soil_moisture_9_27cm"
        const val soilMoisture2781cm="soil_moisture_27_81cm"
        const val uvIndex="uv_index"
        const val uvIndexClearSky="uv_index_clear_sky"
        const val isDay="is_day"
        const val cape="cape"
        const val freezinglevelHeight="freezinglevel_height"
        const val shortwaveRadiation="shortwave_radiation"
        const val directRadiation="direct_radiation"
        const val diffuseRadiation="diffuse_radiation"
        const val directNormalIrradiance="direct_normal_irradiance"
        const val terrestrialRadiation="terrestrial_radiation"
        const val shortwaveRadiationInstant="shortwave_radiation_instant"
        const val directRadiationInstant="direct_radiation_instant"
        const val diffuseRadiationInstant="diffuse_radiation_instant"
        const val directNormalIrradianceInstant="direct_normal_irradiance_instant"
        const val terrestrialRadiationInstant="terrestrial_radiation_instant"
        const val temperature1000hPa="temperature_1000hPa"
        const val temperature975hPa="temperature_975hPa"
        const val temperature950hPa="temperature_950hPa"
        const val temperature925hPa="temperature_925hPa"
        const val temperature900hPa="temperature_900hPa"
        const val temperature850hPa="temperature_850hPa"
        const val temperature800hPa="temperature_800hPa"
        const val temperature700hPa="temperature_700hPa"
        const val temperature600hPa="temperature_600hPa"
        const val temperature500hPa="temperature_500hPa"
        const val temperature400hPa="temperature_400hPa"
        const val temperature300hPa="temperature_300hPa"
        const val temperature250hPa="temperature_250hPa"
        const val temperature200hPa="temperature_200hPa"
        const val temperature150hPa="temperature_150hPa"
        const val temperature100hPa="temperature_100hPa"
        const val temperature70hPa="temperature_70hPa"
        const val temperature50hPa="temperature_50hPa"
        const val temperature30hPa="temperature_30hPa"
        const val relativehumidity1000hPa="relativehumidity_1000hPa"
        const val relativehumidity975hPa="relativehumidity_975hPa"
        const val relativehumidity950hPa="relativehumidity_950hPa"
        const val relativehumidity925hPa="relativehumidity_925hPa"
        const val relativehumidity900hPa="relativehumidity_900hPa"
        const val relativehumidity850hPa="relativehumidity_850hPa"
        const val relativehumidity800hPa="relativehumidity_800hPa"
        const val relativehumidity700hPa="relativehumidity_700hPa"
        const val relativehumidity600hPa="relativehumidity_600hPa"
        const val relativehumidity500hPa="relativehumidity_500hPa"
        const val relativehumidity400hPa="relativehumidity_400hPa"
        const val relativehumidity300hPa="relativehumidity_300hPa"
        const val relativehumidity250hPa="relativehumidity_250hPa"
        const val relativehumidity200hPa="relativehumidity_200hPa"
        const val relativehumidity150hPa="relativehumidity_150hPa"
        const val relativehumidity100hPa="relativehumidity_100hPa"
        const val relativehumidity70hPa="relativehumidity_70hPa"
        const val relativehumidity50hPa="relativehumidity_50hPa"
        const val relativehumidity30hPa="relativehumidity_30hPa"
        const val cloudcover1000hPa="cloudcover_1000hPa"
        const val cloudcover975hPa="cloudcover_975hPa"
        const val cloudcover950hPa="cloudcover_950hPa"
        const val cloudcover925hPa="cloudcover_925hPa"
        const val cloudcover900hPa="cloudcover_900hPa"
        const val cloudcover850hPa="cloudcover_850hPa"
        const val cloudcover800hPa="cloudcover_800hPa"
        const val cloudcover700hPa="cloudcover_700hPa"
        const val cloudcover600hPa="cloudcover_600hPa"
        const val cloudcover500hPa="cloudcover_500hPa"
        const val cloudcover400hPa="cloudcover_400hPa"
        const val cloudcover300hPa="cloudcover_300hPa"
        const val cloudcover250hPa="cloudcover_250hPa"
        const val cloudcover200hPa="cloudcover_200hPa"
        const val cloudcover150hPa="cloudcover_150hPa"
        const val cloudcover100hPa="cloudcover_100hPa"
        const val cloudcover70hPa="cloudcover_70hPa"
        const val cloudcover50hPa="cloudcover_50hPa"
        const val cloudcover30hPa="cloudcover_30hPa"
        const val windspeed1000hPa="windspeed_1000hPa"
        const val windspeed975hPa="windspeed_975hPa"
        const val windspeed950hPa="windspeed_950hPa"
        const val windspeed925hPa="windspeed_925hPa"
        const val windspeed900hPa="windspeed_900hPa"
        const val windspeed850hPa="windspeed_850hPa"
        const val windspeed800hPa="windspeed_800hPa"
        const val windspeed700hPa="windspeed_700hPa"
        const val windspeed600hPa="windspeed_600hPa"
        const val windspeed500hPa="windspeed_500hPa"
        const val windspeed400hPa="windspeed_400hPa"
        const val windspeed300hPa="windspeed_300hPa"
        const val windspeed250hPa="windspeed_250hPa"
        const val windspeed200hPa="windspeed_200hPa"
        const val windspeed150hPa="windspeed_150hPa"
        const val windspeed100hPa="windspeed_100hPa"
        const val windspeed70hPa="windspeed_70hPa"
        const val windspeed50hPa="windspeed_50hPa"
        const val windspeed30hPa="windspeed_30hPa"
        const val winddirection1000hPa="winddirection_1000hPa"
        const val winddirection975hPa="winddirection_975hPa"
        const val winddirection950hPa="winddirection_950hPa"
        const val winddirection925hPa="winddirection_925hPa"
        const val winddirection900hPa="winddirection_900hPa"
        const val winddirection850hPa="winddirection_850hPa"
        const val winddirection800hPa="winddirection_800hPa"
        const val winddirection700hPa="winddirection_700hPa"
        const val winddirection600hPa="winddirection_600hPa"
        const val winddirection500hPa="winddirection_500hPa"
        const val winddirection400hPa="winddirection_400hPa"
        const val winddirection300hPa="winddirection_300hPa"
        const val winddirection250hPa="winddirection_250hPa"
        const val winddirection200hPa="winddirection_200hPa"
        const val winddirection150hPa="winddirection_150hPa"
        const val winddirection100hPa="winddirection_100hPa"
        const val winddirection70hPa="winddirection_70hPa"
        const val winddirection50hPa="winddirection_50hPa"
        const val winddirection30hPa="winddirection_30hPa"
        const val geopotentialHeight1000hPa="geopotential_height_1000hPa"
        const val geopotentialHeight975hPa="geopotential_height_975hPa"
        const val geopotentialHeight950hPa="geopotential_height_950hPa"
        const val geopotentialHeight925hPa="geopotential_height_925hPa"
        const val geopotentialHeight900hPa="geopotential_height_900hPa"
        const val geopotentialHeight850hPa="geopotential_height_850hPa"
        const val geopotentialHeight800hPa="geopotential_height_800hPa"
        const val geopotentialHeight700hPa="geopotential_height_700hPa"
        const val geopotentialHeight600hPa="geopotential_height_600hPa"
        const val geopotentialHeight500hPa="geopotential_height_500hPa"
        const val geopotentialHeight400hPa="geopotential_height_400hPa"
        const val geopotentialHeight300hPa="geopotential_height_300hPa"
        const val geopotentialHeight250hPa="geopotential_height_250hPa"
        const val geopotentialHeight200hPa="geopotential_height_200hPa"
        const val geopotentialHeight150hPa="geopotential_height_150hPa"
        const val geopotentialHeight100hPa="geopotential_height_100hPa"
        const val geopotentialHeight70hPa="geopotential_height_70hPa"
        const val geopotentialHeight50hPa="geopotential_height_50hPa"
        const val geopotentialHeight30hPa="geopotential_height_30hPa"
    }

}
