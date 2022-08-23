package com.openmeteo.api.forecast.serials

import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.time.TimeFormat
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    val time: TimeFormat,
    val temperature_2m: TemperatureUnit? = null,
    val relativehumidity_2m: String? = null,
    val dewpoint_2m: TemperatureUnit? = null,
    val apparent_temperature: TemperatureUnit? = null,
    val pressure_msl: String? = null,
    val surface_pressure: String? = null,
    val cloudcover: String? = null,
    val cloudcover_low: String? = null,
    val cloudcover_mid: String? = null,
    val cloudcover_high: String? = null,
    val windspeed_10m: WindSpeedUnit? = null,
    val windspeed_80m: WindSpeedUnit? = null,
    val windspeed_120m: WindSpeedUnit? = null,
    val windspeed_180m: WindSpeedUnit? = null,
    val winddirection_10m: String? = null,
    val winddirection_80m: String? = null,
    val winddirection_120m: String? = null,
    val winddirection_180m: String? = null,
    val windgusts_10m: WindSpeedUnit? = null,
    val shortwave_radiation: String? = null,
    val direct_radiation: String? = null,
    val direct_normal_irradiance: String? = null,
    val diffuse_radiation: String? = null,
    val vapor_pressure_deficit: String? = null,
    val evapotranspiration: PrecipitationUnit? = null,
    val et0_fao_evapotranspiration: PrecipitationUnit? = null,
    val precipitation: PrecipitationUnit? = null,
    val snowfall: PrecipitationUnit? = null,
    val rain: PrecipitationUnit? = null,
    val showers: PrecipitationUnit? = null,
    val weathercode: String? = null,
    val snow_depth: String? = null,
    val freezinglevel_height: String? = null,
    val soil_temperature_0cm: TemperatureUnit? = null,
    val soil_temperature_6cm: TemperatureUnit? = null,
    val soil_temperature_18cm: TemperatureUnit? = null,
    val soil_temperature_54cm: TemperatureUnit? = null,
    val soil_moisture_0_1cm: String? = null,
    val soil_moisture_1_3cm: String? = null,
    val soil_moisture_3_9cm: String? = null,
    val soil_moisture_9_27cm: String? = null,
    val soil_moisture_27_81cm: String? = null,
)
