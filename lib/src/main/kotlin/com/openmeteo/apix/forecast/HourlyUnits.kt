package com.openmeteo.apix.forecast

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.TimeFormat
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    override val time: TimeFormat,
    @SerialName("temperature_2m")
    val temperature2m: Unit? = null,
    @SerialName("relativehumidity_2m")
    val relativehumidity2m: Unit? = null,
    @SerialName("dewpoint_2m")
    val dewpoint2m: Unit? = null,
    @SerialName("apparent_temperature")
    val apparentTemperature: Unit? = null,
    @SerialName("precipitation")
    val precipitation: Unit? = null,
    @SerialName("rain")
    val rain: Unit? = null,
    @SerialName("showers")
    val showers: Unit? = null,
    @SerialName("snowfall")
    val snowfall: Unit? = null,
    @SerialName("snow_depth")
    val snowDepth: Unit? = null,
    @SerialName("freezinglevel_height")
    val freezinglevelHeight: Unit? = null,
    @SerialName("weathercode")
    val weathercode: Unit? = null,
    @SerialName("pressure_msl")
    val pressureMsl: Unit? = null,
    @SerialName("surface_pressure")
    val surfacePressure: Unit? = null,
    @SerialName("cloudcover")
    val cloudcover: Unit? = null,
    @SerialName("cloudcover_low")
    val cloudcoverLow: Unit? = null,
    @SerialName("cloudcover_mid")
    val cloudcoverMid: Unit? = null,
    @SerialName("cloudcover_high")
    val cloudcoverHigh: Unit? = null,
    @SerialName("evapotranspiration")
    val evapotranspiration: Unit? = null,
    @SerialName("et0_fao_evapotranspiration")
    val et0FaoEvapotranspiration: Unit? = null,
    @SerialName("vapor_pressure_deficit")
    val vaporPressureDeficit: Unit? = null,
    @SerialName("latent_heatflux")
    val latentHeatflux: Unit? = null,
    @SerialName("sensible_heatflux")
    val sensibleHeatflux: Unit? = null,
    @SerialName("windspeed_10m")
    val windspeed10m: Unit? = null,
    @SerialName("windspeed_80m")
    val windspeed80m: Unit? = null,
    @SerialName("windspeed_120m")
    val windspeed120m: Unit? = null,
    @SerialName("windspeed_180m")
    val windspeed180m: Unit? = null,
    @SerialName("winddirection_10m")
    val winddirection10m: Unit? = null,
    @SerialName("winddirection_80m")
    val winddirection80m: Unit? = null,
    @SerialName("winddirection_120m")
    val winddirection120m: Unit? = null,
    @SerialName("winddirection_180m")
    val winddirection180m: Unit? = null,
    @SerialName("windgusts_10m")
    val windgusts10m: Unit? = null,
    @SerialName("soil_temperature_0cm")
    val soilTemperature0cm: Unit? = null,
    @SerialName("soil_temperature_6cm")
    val soilTemperature6cm: Unit? = null,
    @SerialName("soil_temperature_18cm")
    val soilTemperature18cm: Unit? = null,
    @SerialName("soil_temperature_54cm")
    val soilTemperature54cm: Unit? = null,
    @SerialName("soil_moisture_0_1cm")
    val soilMoisture01cm: Unit? = null,
    @SerialName("soil_moisture_1_3cm")
    val soilMoisture13cm: Unit? = null,
    @SerialName("soil_moisture_3_9cm")
    val soilMoisture39cm: Unit? = null,
    @SerialName("soil_moisture_9_27cm")
    val soilMoisture927cm: Unit? = null,
    @SerialName("soil_moisture_27_81cm")
    val soilMoisture2781cm: Unit? = null,
    @SerialName("shortwave_radiation")
    val shortwaveRadiation: Unit? = null,
    @SerialName("direct_radiation")
    val directRadiation: Unit? = null,
    @SerialName("diffuse_radiation")
    val diffuseRadiation: Unit? = null,
    @SerialName("direct_normal_irradiance")
    val directNormalIrradiance: Unit? = null,
    @SerialName("terrestrial_radiation")
    val terrestrialRadiation: Unit? = null,
    @SerialName("shortwave_radiation_instant")
    val shortwaveRadiationInstant: Unit? = null,
    @SerialName("direct_radiation_instant")
    val directRadiationInstant: Unit? = null,
    @SerialName("diffuse_radiation_instant")
    val diffuseRadiationInstant: Unit? = null,
    @SerialName("direct_normal_irradiance_instant")
    val directNormalIrradianceInstant: Unit? = null,
    @SerialName("terrestrial_radiation_instant")
    val terrestrialRadiationInstant: Unit? = null,
    @SerialName("temperature_1000hPa")
    val temperature1000hPa: Unit? = null,
    @SerialName("temperature_975hPa")
    val temperature975hPa: Unit? = null,
    @SerialName("temperature_950hPa")
    val temperature950hPa: Unit? = null,
    @SerialName("temperature_925hPa")
    val temperature925hPa: Unit? = null,
    @SerialName("temperature_900hPa")
    val temperature900hPa: Unit? = null,
    @SerialName("temperature_850hPa")
    val temperature850hPa: Unit? = null,
    @SerialName("temperature_800hPa")
    val temperature800hPa: Unit? = null,
    @SerialName("temperature_700hPa")
    val temperature700hPa: Unit? = null,
    @SerialName("temperature_600hPa")
    val temperature600hPa: Unit? = null,
    @SerialName("temperature_500hPa")
    val temperature500hPa: Unit? = null,
    @SerialName("temperature_400hPa")
    val temperature400hPa: Unit? = null,
    @SerialName("temperature_300hPa")
    val temperature300hPa: Unit? = null,
    @SerialName("temperature_250hPa")
    val temperature250hPa: Unit? = null,
    @SerialName("temperature_200hPa")
    val temperature200hPa: Unit? = null,
    @SerialName("temperature_150hPa")
    val temperature150hPa: Unit? = null,
    @SerialName("temperature_100hPa")
    val temperature100hPa: Unit? = null,
    @SerialName("temperature_70hPa")
    val temperature70hPa: Unit? = null,
    @SerialName("temperature_50hPa")
    val temperature50hPa: Unit? = null,
    @SerialName("temperature_30hPa")
    val temperature30hPa: Unit? = null,
    @SerialName("dewpoint_1000hPa")
    val dewpoint1000hPa: Unit? = null,
    @SerialName("dewpoint_975hPa")
    val dewpoint975hPa: Unit? = null,
    @SerialName("dewpoint_950hPa")
    val dewpoint950hPa: Unit? = null,
    @SerialName("dewpoint_925hPa")
    val dewpoint925hPa: Unit? = null,
    @SerialName("dewpoint_900hPa")
    val dewpoint900hPa: Unit? = null,
    @SerialName("dewpoint_850hPa")
    val dewpoint850hPa: Unit? = null,
    @SerialName("dewpoint_800hPa")
    val dewpoint800hPa: Unit? = null,
    @SerialName("dewpoint_700hPa")
    val dewpoint700hPa: Unit? = null,
    @SerialName("dewpoint_600hPa")
    val dewpoint600hPa: Unit? = null,
    @SerialName("dewpoint_500hPa")
    val dewpoint500hPa: Unit? = null,
    @SerialName("dewpoint_400hPa")
    val dewpoint400hPa: Unit? = null,
    @SerialName("dewpoint_300hPa")
    val dewpoint300hPa: Unit? = null,
    @SerialName("dewpoint_250hPa")
    val dewpoint250hPa: Unit? = null,
    @SerialName("dewpoint_200hPa")
    val dewpoint200hPa: Unit? = null,
    @SerialName("dewpoint_150hPa")
    val dewpoint150hPa: Unit? = null,
    @SerialName("dewpoint_100hPa")
    val dewpoint100hPa: Unit? = null,
    @SerialName("dewpoint_70hPa")
    val dewpoint70hPa: Unit? = null,
    @SerialName("dewpoint_50hPa")
    val dewpoint50hPa: Unit? = null,
    @SerialName("dewpoint_30hPa")
    val dewpoint30hPa: Unit? = null,
    @SerialName("relativehumidity_1000hPa")
    val relativehumidity1000hPa: Unit? = null,
    @SerialName("relativehumidity_975hPa")
    val relativehumidity975hPa: Unit? = null,
    @SerialName("relativehumidity_950hPa")
    val relativehumidity950hPa: Unit? = null,
    @SerialName("relativehumidity_925hPa")
    val relativehumidity925hPa: Unit? = null,
    @SerialName("relativehumidity_900hPa")
    val relativehumidity900hPa: Unit? = null,
    @SerialName("relativehumidity_850hPa")
    val relativehumidity850hPa: Unit? = null,
    @SerialName("relativehumidity_800hPa")
    val relativehumidity800hPa: Unit? = null,
    @SerialName("relativehumidity_700hPa")
    val relativehumidity700hPa: Unit? = null,
    @SerialName("relativehumidity_600hPa")
    val relativehumidity600hPa: Unit? = null,
    @SerialName("relativehumidity_500hPa")
    val relativehumidity500hPa: Unit? = null,
    @SerialName("relativehumidity_400hPa")
    val relativehumidity400hPa: Unit? = null,
    @SerialName("relativehumidity_300hPa")
    val relativehumidity300hPa: Unit? = null,
    @SerialName("relativehumidity_250hPa")
    val relativehumidity250hPa: Unit? = null,
    @SerialName("relativehumidity_200hPa")
    val relativehumidity200hPa: Unit? = null,
    @SerialName("relativehumidity_150hPa")
    val relativehumidity150hPa: Unit? = null,
    @SerialName("relativehumidity_100hPa")
    val relativehumidity100hPa: Unit? = null,
    @SerialName("relativehumidity_70hPa")
    val relativehumidity70hPa: Unit? = null,
    @SerialName("relativehumidity_50hPa")
    val relativehumidity50hPa: Unit? = null,
    @SerialName("relativehumidity_30hPa")
    val relativehumidity30hPa: Unit? = null,
    @SerialName("cloudcover_1000hPa")
    val cloudcover1000hPa: Unit? = null,
    @SerialName("cloudcover_975hPa")
    val cloudcover975hPa: Unit? = null,
    @SerialName("cloudcover_950hPa")
    val cloudcover950hPa: Unit? = null,
    @SerialName("cloudcover_925hPa")
    val cloudcover925hPa: Unit? = null,
    @SerialName("cloudcover_900hPa")
    val cloudcover900hPa: Unit? = null,
    @SerialName("cloudcover_850hPa")
    val cloudcover850hPa: Unit? = null,
    @SerialName("cloudcover_800hPa")
    val cloudcover800hPa: Unit? = null,
    @SerialName("cloudcover_700hPa")
    val cloudcover700hPa: Unit? = null,
    @SerialName("cloudcover_600hPa")
    val cloudcover600hPa: Unit? = null,
    @SerialName("cloudcover_500hPa")
    val cloudcover500hPa: Unit? = null,
    @SerialName("cloudcover_400hPa")
    val cloudcover400hPa: Unit? = null,
    @SerialName("cloudcover_300hPa")
    val cloudcover300hPa: Unit? = null,
    @SerialName("cloudcover_250hPa")
    val cloudcover250hPa: Unit? = null,
    @SerialName("cloudcover_200hPa")
    val cloudcover200hPa: Unit? = null,
    @SerialName("cloudcover_150hPa")
    val cloudcover150hPa: Unit? = null,
    @SerialName("cloudcover_100hPa")
    val cloudcover100hPa: Unit? = null,
    @SerialName("cloudcover_70hPa")
    val cloudcover70hPa: Unit? = null,
    @SerialName("cloudcover_50hPa")
    val cloudcover50hPa: Unit? = null,
    @SerialName("cloudcover_30hPa")
    val cloudcover30hPa: Unit? = null,
    @SerialName("windspeed_1000hPa")
    val windspeed1000hPa: Unit? = null,
    @SerialName("windspeed_975hPa")
    val windspeed975hPa: Unit? = null,
    @SerialName("windspeed_950hPa")
    val windspeed950hPa: Unit? = null,
    @SerialName("windspeed_925hPa")
    val windspeed925hPa: Unit? = null,
    @SerialName("windspeed_900hPa")
    val windspeed900hPa: Unit? = null,
    @SerialName("windspeed_850hPa")
    val windspeed850hPa: Unit? = null,
    @SerialName("windspeed_800hPa")
    val windspeed800hPa: Unit? = null,
    @SerialName("windspeed_700hPa")
    val windspeed700hPa: Unit? = null,
    @SerialName("windspeed_600hPa")
    val windspeed600hPa: Unit? = null,
    @SerialName("windspeed_500hPa")
    val windspeed500hPa: Unit? = null,
    @SerialName("windspeed_400hPa")
    val windspeed400hPa: Unit? = null,
    @SerialName("windspeed_300hPa")
    val windspeed300hPa: Unit? = null,
    @SerialName("windspeed_250hPa")
    val windspeed250hPa: Unit? = null,
    @SerialName("windspeed_200hPa")
    val windspeed200hPa: Unit? = null,
    @SerialName("windspeed_150hPa")
    val windspeed150hPa: Unit? = null,
    @SerialName("windspeed_100hPa")
    val windspeed100hPa: Unit? = null,
    @SerialName("windspeed_70hPa")
    val windspeed70hPa: Unit? = null,
    @SerialName("windspeed_50hPa")
    val windspeed50hPa: Unit? = null,
    @SerialName("windspeed_30hPa")
    val windspeed30hPa: Unit? = null,
    @SerialName("winddirection_1000hPa")
    val winddirection1000hPa: Unit? = null,
    @SerialName("winddirection_975hPa")
    val winddirection975hPa: Unit? = null,
    @SerialName("winddirection_950hPa")
    val winddirection950hPa: Unit? = null,
    @SerialName("winddirection_925hPa")
    val winddirection925hPa: Unit? = null,
    @SerialName("winddirection_900hPa")
    val winddirection900hPa: Unit? = null,
    @SerialName("winddirection_850hPa")
    val winddirection850hPa: Unit? = null,
    @SerialName("winddirection_800hPa")
    val winddirection800hPa: Unit? = null,
    @SerialName("winddirection_700hPa")
    val winddirection700hPa: Unit? = null,
    @SerialName("winddirection_600hPa")
    val winddirection600hPa: Unit? = null,
    @SerialName("winddirection_500hPa")
    val winddirection500hPa: Unit? = null,
    @SerialName("winddirection_400hPa")
    val winddirection400hPa: Unit? = null,
    @SerialName("winddirection_300hPa")
    val winddirection300hPa: Unit? = null,
    @SerialName("winddirection_250hPa")
    val winddirection250hPa: Unit? = null,
    @SerialName("winddirection_200hPa")
    val winddirection200hPa: Unit? = null,
    @SerialName("winddirection_150hPa")
    val winddirection150hPa: Unit? = null,
    @SerialName("winddirection_100hPa")
    val winddirection100hPa: Unit? = null,
    @SerialName("winddirection_70hPa")
    val winddirection70hPa: Unit? = null,
    @SerialName("winddirection_50hPa")
    val winddirection50hPa: Unit? = null,
    @SerialName("winddirection_30hPa")
    val winddirection30hPa: Unit? = null,
    @SerialName("geopotential_height_1000hPa")
    val geopotentialHeight1000hPa: Unit? = null,
    @SerialName("geopotential_height_975hPa")
    val geopotentialHeight975hPa: Unit? = null,
    @SerialName("geopotential_height_950hPa")
    val geopotentialHeight950hPa: Unit? = null,
    @SerialName("geopotential_height_925hPa")
    val geopotentialHeight925hPa: Unit? = null,
    @SerialName("geopotential_height_900hPa")
    val geopotentialHeight900hPa: Unit? = null,
    @SerialName("geopotential_height_850hPa")
    val geopotentialHeight850hPa: Unit? = null,
    @SerialName("geopotential_height_800hPa")
    val geopotentialHeight800hPa: Unit? = null,
    @SerialName("geopotential_height_700hPa")
    val geopotentialHeight700hPa: Unit? = null,
    @SerialName("geopotential_height_600hPa")
    val geopotentialHeight600hPa: Unit? = null,
    @SerialName("geopotential_height_500hPa")
    val geopotentialHeight500hPa: Unit? = null,
    @SerialName("geopotential_height_400hPa")
    val geopotentialHeight400hPa: Unit? = null,
    @SerialName("geopotential_height_300hPa")
    val geopotentialHeight300hPa: Unit? = null,
    @SerialName("geopotential_height_250hPa")
    val geopotentialHeight250hPa: Unit? = null,
    @SerialName("geopotential_height_200hPa")
    val geopotentialHeight200hPa: Unit? = null,
    @SerialName("geopotential_height_150hPa")
    val geopotentialHeight150hPa: Unit? = null,
    @SerialName("geopotential_height_100hPa")
    val geopotentialHeight100hPa: Unit? = null,
    @SerialName("geopotential_height_70hPa")
    val geopotentialHeight70hPa: Unit? = null,
    @SerialName("geopotential_height_50hPa")
    val geopotentialHeight50hPa: Unit? = null,
    @SerialName("geopotential_height_30hPa")
    val geopotentialHeight30hPa: Unit? = null,
) : ResponseHourly.Units
