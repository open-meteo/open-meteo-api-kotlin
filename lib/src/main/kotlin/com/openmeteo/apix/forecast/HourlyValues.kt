package com.openmeteo.apix.forecast

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyValues(
    override val time: Array<Time>,
    @SerialName("temperature_2m")
    val temperature2m: Array<Float?>? = null,
    @SerialName("relativehumidity_2m")
    val relativehumidity2m: Array<Float?>? = null,
    @SerialName("dewpoint_2m")
    val dewpoint2m: Array<Float?>? = null,
    @SerialName("apparent_temperature")
    val apparentTemperature: Array<Float?>? = null,
    @SerialName("precipitation")
    val precipitation: Array<Float?>? = null,
    @SerialName("rain")
    val rain: Array<Float?>? = null,
    @SerialName("showers")
    val showers: Array<Float?>? = null,
    @SerialName("snowfall")
    val snowfall: Array<Float?>? = null,
    @SerialName("snow_depth")
    val snowDepth: Array<Float?>? = null,
    @SerialName("freezinglevel_height")
    val freezinglevelHeight: Array<Float?>? = null,
    @SerialName("weathercode")
    val weathercode: Array<Float?>? = null,
    @SerialName("pressure_msl")
    val pressureMsl: Array<Float?>? = null,
    @SerialName("surface_pressure")
    val surfacePressure: Array<Float?>? = null,
    @SerialName("cloudcover")
    val cloudcover: Array<Float?>? = null,
    @SerialName("cloudcover_low")
    val cloudcoverLow: Array<Float?>? = null,
    @SerialName("cloudcover_mid")
    val cloudcoverMid: Array<Float?>? = null,
    @SerialName("cloudcover_high")
    val cloudcoverHigh: Array<Float?>? = null,
    @SerialName("evapotranspiration")
    val evapotranspiration: Array<Float?>? = null,
    @SerialName("et0_fao_evapotranspiration")
    val et0FaoEvapotranspiration: Array<Float?>? = null,
    @SerialName("vapor_pressure_deficit")
    val vaporPressureDeficit: Array<Float?>? = null,
    @SerialName("latent_heatflux")
    val latentHeatflux: Array<Float?>? = null,
    @SerialName("sensible_heatflux")
    val sensibleHeatflux: Array<Float?>? = null,
    @SerialName("windspeed_10m")
    val windspeed10m: Array<Float?>? = null,
    @SerialName("windspeed_80m")
    val windspeed80m: Array<Float?>? = null,
    @SerialName("windspeed_120m")
    val windspeed120m: Array<Float?>? = null,
    @SerialName("windspeed_180m")
    val windspeed180m: Array<Float?>? = null,
    @SerialName("winddirection_10m")
    val winddirection10m: Array<Float?>? = null,
    @SerialName("winddirection_80m")
    val winddirection80m: Array<Float?>? = null,
    @SerialName("winddirection_120m")
    val winddirection120m: Array<Float?>? = null,
    @SerialName("winddirection_180m")
    val winddirection180m: Array<Float?>? = null,
    @SerialName("windgusts_10m")
    val windgusts10m: Array<Float?>? = null,
    @SerialName("soil_temperature_0cm")
    val soilTemperature0cm: Array<Float?>? = null,
    @SerialName("soil_temperature_6cm")
    val soilTemperature6cm: Array<Float?>? = null,
    @SerialName("soil_temperature_18cm")
    val soilTemperature18cm: Array<Float?>? = null,
    @SerialName("soil_temperature_54cm")
    val soilTemperature54cm: Array<Float?>? = null,
    @SerialName("soil_moisture_0_1cm")
    val soilMoisture01cm: Array<Float?>? = null,
    @SerialName("soil_moisture_1_3cm")
    val soilMoisture13cm: Array<Float?>? = null,
    @SerialName("soil_moisture_3_9cm")
    val soilMoisture39cm: Array<Float?>? = null,
    @SerialName("soil_moisture_9_27cm")
    val soilMoisture927cm: Array<Float?>? = null,
    @SerialName("soil_moisture_27_81cm")
    val soilMoisture2781cm: Array<Float?>? = null,
    @SerialName("shortwave_radiation")
    val shortwaveRadiation: Array<Float?>? = null,
    @SerialName("direct_radiation")
    val directRadiation: Array<Float?>? = null,
    @SerialName("diffuse_radiation")
    val diffuseRadiation: Array<Float?>? = null,
    @SerialName("direct_normal_irradiance")
    val directNormalIrradiance: Array<Float?>? = null,
    @SerialName("terrestrial_radiation")
    val terrestrialRadiation: Array<Float?>? = null,
    @SerialName("shortwave_radiation_instant")
    val shortwaveRadiationInstant: Array<Float?>? = null,
    @SerialName("direct_radiation_instant")
    val directRadiationInstant: Array<Float?>? = null,
    @SerialName("diffuse_radiation_instant")
    val diffuseRadiationInstant: Array<Float?>? = null,
    @SerialName("direct_normal_irradiance_instant")
    val directNormalIrradianceInstant: Array<Float?>? = null,
    @SerialName("terrestrial_radiation_instant")
    val terrestrialRadiationInstant: Array<Float?>? = null,
    @SerialName("temperature_1000hPa")
    val temperature1000hPa: Array<Float?>? = null,
    @SerialName("temperature_975hPa")
    val temperature975hPa: Array<Float?>? = null,
    @SerialName("temperature_950hPa")
    val temperature950hPa: Array<Float?>? = null,
    @SerialName("temperature_925hPa")
    val temperature925hPa: Array<Float?>? = null,
    @SerialName("temperature_900hPa")
    val temperature900hPa: Array<Float?>? = null,
    @SerialName("temperature_850hPa")
    val temperature850hPa: Array<Float?>? = null,
    @SerialName("temperature_800hPa")
    val temperature800hPa: Array<Float?>? = null,
    @SerialName("temperature_700hPa")
    val temperature700hPa: Array<Float?>? = null,
    @SerialName("temperature_600hPa")
    val temperature600hPa: Array<Float?>? = null,
    @SerialName("temperature_500hPa")
    val temperature500hPa: Array<Float?>? = null,
    @SerialName("temperature_400hPa")
    val temperature400hPa: Array<Float?>? = null,
    @SerialName("temperature_300hPa")
    val temperature300hPa: Array<Float?>? = null,
    @SerialName("temperature_250hPa")
    val temperature250hPa: Array<Float?>? = null,
    @SerialName("temperature_200hPa")
    val temperature200hPa: Array<Float?>? = null,
    @SerialName("temperature_150hPa")
    val temperature150hPa: Array<Float?>? = null,
    @SerialName("temperature_100hPa")
    val temperature100hPa: Array<Float?>? = null,
    @SerialName("temperature_70hPa")
    val temperature70hPa: Array<Float?>? = null,
    @SerialName("temperature_50hPa")
    val temperature50hPa: Array<Float?>? = null,
    @SerialName("temperature_30hPa")
    val temperature30hPa: Array<Float?>? = null,
    @SerialName("dewpoint_1000hPa")
    val dewpoint1000hPa: Array<Float?>? = null,
    @SerialName("dewpoint_975hPa")
    val dewpoint975hPa: Array<Float?>? = null,
    @SerialName("dewpoint_950hPa")
    val dewpoint950hPa: Array<Float?>? = null,
    @SerialName("dewpoint_925hPa")
    val dewpoint925hPa: Array<Float?>? = null,
    @SerialName("dewpoint_900hPa")
    val dewpoint900hPa: Array<Float?>? = null,
    @SerialName("dewpoint_850hPa")
    val dewpoint850hPa: Array<Float?>? = null,
    @SerialName("dewpoint_800hPa")
    val dewpoint800hPa: Array<Float?>? = null,
    @SerialName("dewpoint_700hPa")
    val dewpoint700hPa: Array<Float?>? = null,
    @SerialName("dewpoint_600hPa")
    val dewpoint600hPa: Array<Float?>? = null,
    @SerialName("dewpoint_500hPa")
    val dewpoint500hPa: Array<Float?>? = null,
    @SerialName("dewpoint_400hPa")
    val dewpoint400hPa: Array<Float?>? = null,
    @SerialName("dewpoint_300hPa")
    val dewpoint300hPa: Array<Float?>? = null,
    @SerialName("dewpoint_250hPa")
    val dewpoint250hPa: Array<Float?>? = null,
    @SerialName("dewpoint_200hPa")
    val dewpoint200hPa: Array<Float?>? = null,
    @SerialName("dewpoint_150hPa")
    val dewpoint150hPa: Array<Float?>? = null,
    @SerialName("dewpoint_100hPa")
    val dewpoint100hPa: Array<Float?>? = null,
    @SerialName("dewpoint_70hPa")
    val dewpoint70hPa: Array<Float?>? = null,
    @SerialName("dewpoint_50hPa")
    val dewpoint50hPa: Array<Float?>? = null,
    @SerialName("dewpoint_30hPa")
    val dewpoint30hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_1000hPa")
    val relativehumidity1000hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_975hPa")
    val relativehumidity975hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_950hPa")
    val relativehumidity950hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_925hPa")
    val relativehumidity925hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_900hPa")
    val relativehumidity900hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_850hPa")
    val relativehumidity850hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_800hPa")
    val relativehumidity800hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_700hPa")
    val relativehumidity700hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_600hPa")
    val relativehumidity600hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_500hPa")
    val relativehumidity500hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_400hPa")
    val relativehumidity400hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_300hPa")
    val relativehumidity300hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_250hPa")
    val relativehumidity250hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_200hPa")
    val relativehumidity200hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_150hPa")
    val relativehumidity150hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_100hPa")
    val relativehumidity100hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_70hPa")
    val relativehumidity70hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_50hPa")
    val relativehumidity50hPa: Array<Float?>? = null,
    @SerialName("relativehumidity_30hPa")
    val relativehumidity30hPa: Array<Float?>? = null,
    @SerialName("cloudcover_1000hPa")
    val cloudcover1000hPa: Array<Float?>? = null,
    @SerialName("cloudcover_975hPa")
    val cloudcover975hPa: Array<Float?>? = null,
    @SerialName("cloudcover_950hPa")
    val cloudcover950hPa: Array<Float?>? = null,
    @SerialName("cloudcover_925hPa")
    val cloudcover925hPa: Array<Float?>? = null,
    @SerialName("cloudcover_900hPa")
    val cloudcover900hPa: Array<Float?>? = null,
    @SerialName("cloudcover_850hPa")
    val cloudcover850hPa: Array<Float?>? = null,
    @SerialName("cloudcover_800hPa")
    val cloudcover800hPa: Array<Float?>? = null,
    @SerialName("cloudcover_700hPa")
    val cloudcover700hPa: Array<Float?>? = null,
    @SerialName("cloudcover_600hPa")
    val cloudcover600hPa: Array<Float?>? = null,
    @SerialName("cloudcover_500hPa")
    val cloudcover500hPa: Array<Float?>? = null,
    @SerialName("cloudcover_400hPa")
    val cloudcover400hPa: Array<Float?>? = null,
    @SerialName("cloudcover_300hPa")
    val cloudcover300hPa: Array<Float?>? = null,
    @SerialName("cloudcover_250hPa")
    val cloudcover250hPa: Array<Float?>? = null,
    @SerialName("cloudcover_200hPa")
    val cloudcover200hPa: Array<Float?>? = null,
    @SerialName("cloudcover_150hPa")
    val cloudcover150hPa: Array<Float?>? = null,
    @SerialName("cloudcover_100hPa")
    val cloudcover100hPa: Array<Float?>? = null,
    @SerialName("cloudcover_70hPa")
    val cloudcover70hPa: Array<Float?>? = null,
    @SerialName("cloudcover_50hPa")
    val cloudcover50hPa: Array<Float?>? = null,
    @SerialName("cloudcover_30hPa")
    val cloudcover30hPa: Array<Float?>? = null,
    @SerialName("windspeed_1000hPa")
    val windspeed1000hPa: Array<Float?>? = null,
    @SerialName("windspeed_975hPa")
    val windspeed975hPa: Array<Float?>? = null,
    @SerialName("windspeed_950hPa")
    val windspeed950hPa: Array<Float?>? = null,
    @SerialName("windspeed_925hPa")
    val windspeed925hPa: Array<Float?>? = null,
    @SerialName("windspeed_900hPa")
    val windspeed900hPa: Array<Float?>? = null,
    @SerialName("windspeed_850hPa")
    val windspeed850hPa: Array<Float?>? = null,
    @SerialName("windspeed_800hPa")
    val windspeed800hPa: Array<Float?>? = null,
    @SerialName("windspeed_700hPa")
    val windspeed700hPa: Array<Float?>? = null,
    @SerialName("windspeed_600hPa")
    val windspeed600hPa: Array<Float?>? = null,
    @SerialName("windspeed_500hPa")
    val windspeed500hPa: Array<Float?>? = null,
    @SerialName("windspeed_400hPa")
    val windspeed400hPa: Array<Float?>? = null,
    @SerialName("windspeed_300hPa")
    val windspeed300hPa: Array<Float?>? = null,
    @SerialName("windspeed_250hPa")
    val windspeed250hPa: Array<Float?>? = null,
    @SerialName("windspeed_200hPa")
    val windspeed200hPa: Array<Float?>? = null,
    @SerialName("windspeed_150hPa")
    val windspeed150hPa: Array<Float?>? = null,
    @SerialName("windspeed_100hPa")
    val windspeed100hPa: Array<Float?>? = null,
    @SerialName("windspeed_70hPa")
    val windspeed70hPa: Array<Float?>? = null,
    @SerialName("windspeed_50hPa")
    val windspeed50hPa: Array<Float?>? = null,
    @SerialName("windspeed_30hPa")
    val windspeed30hPa: Array<Float?>? = null,
    @SerialName("winddirection_1000hPa")
    val winddirection1000hPa: Array<Float?>? = null,
    @SerialName("winddirection_975hPa")
    val winddirection975hPa: Array<Float?>? = null,
    @SerialName("winddirection_950hPa")
    val winddirection950hPa: Array<Float?>? = null,
    @SerialName("winddirection_925hPa")
    val winddirection925hPa: Array<Float?>? = null,
    @SerialName("winddirection_900hPa")
    val winddirection900hPa: Array<Float?>? = null,
    @SerialName("winddirection_850hPa")
    val winddirection850hPa: Array<Float?>? = null,
    @SerialName("winddirection_800hPa")
    val winddirection800hPa: Array<Float?>? = null,
    @SerialName("winddirection_700hPa")
    val winddirection700hPa: Array<Float?>? = null,
    @SerialName("winddirection_600hPa")
    val winddirection600hPa: Array<Float?>? = null,
    @SerialName("winddirection_500hPa")
    val winddirection500hPa: Array<Float?>? = null,
    @SerialName("winddirection_400hPa")
    val winddirection400hPa: Array<Float?>? = null,
    @SerialName("winddirection_300hPa")
    val winddirection300hPa: Array<Float?>? = null,
    @SerialName("winddirection_250hPa")
    val winddirection250hPa: Array<Float?>? = null,
    @SerialName("winddirection_200hPa")
    val winddirection200hPa: Array<Float?>? = null,
    @SerialName("winddirection_150hPa")
    val winddirection150hPa: Array<Float?>? = null,
    @SerialName("winddirection_100hPa")
    val winddirection100hPa: Array<Float?>? = null,
    @SerialName("winddirection_70hPa")
    val winddirection70hPa: Array<Float?>? = null,
    @SerialName("winddirection_50hPa")
    val winddirection50hPa: Array<Float?>? = null,
    @SerialName("winddirection_30hPa")
    val winddirection30hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_1000hPa")
    val geopotentialHeight1000hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_975hPa")
    val geopotentialHeight975hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_950hPa")
    val geopotentialHeight950hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_925hPa")
    val geopotentialHeight925hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_900hPa")
    val geopotentialHeight900hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_850hPa")
    val geopotentialHeight850hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_800hPa")
    val geopotentialHeight800hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_700hPa")
    val geopotentialHeight700hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_600hPa")
    val geopotentialHeight600hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_500hPa")
    val geopotentialHeight500hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_400hPa")
    val geopotentialHeight400hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_300hPa")
    val geopotentialHeight300hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_250hPa")
    val geopotentialHeight250hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_200hPa")
    val geopotentialHeight200hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_150hPa")
    val geopotentialHeight150hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_100hPa")
    val geopotentialHeight100hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_70hPa")
    val geopotentialHeight70hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_50hPa")
    val geopotentialHeight50hPa: Array<Float?>? = null,
    @SerialName("geopotential_height_30hPa")
    val geopotentialHeight30hPa: Array<Float?>? = null,
) : ResponseHourly.Values
