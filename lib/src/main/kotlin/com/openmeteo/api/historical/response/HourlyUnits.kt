package com.openmeteo.api.historical.response

import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.time.TimeFormat
import com.openmeteo.api.common.units.PressureUnit
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    val time: TimeFormat,
    val temperature_2m: TemperatureUnit? = null,
    val relativehumidity_2m: String? = null,
    val dewpoint_2m: TemperatureUnit? = null,
    val apparent_temperature: TemperatureUnit? = null,
    val pressure_msl: PressureUnit? = null,
    val surface_pressure: PressureUnit? = null,
    val precipitation: PrecipitationUnit? = null,
    val rain: PrecipitationUnit? = null,
    val snowfall: PrecipitationUnit? = null,
    val cloudcover: String? = null,
    val cloudcover_low: String? = null,
    val cloudcover_mid: String? = null,
    val cloudcover_high: String? = null,
    val shortwave_radiation: String? = null,
    val direct_radiation: String? = null,
    val direct_normal_irradiance: String? = null,
    val diffuse_radiation: String? = null,
    val windspeed_10m: WindSpeedUnit? = null,
    val windspeed_100m: WindSpeedUnit? = null,
    val winddirection_10m: WindSpeedUnit? = null,
    val winddirection_100m: WindSpeedUnit? = null,
    val windgusts_10m: WindSpeedUnit? = null,
    val et0_fao_evapotranspiration: PrecipitationUnit? = null,
    val vapor_pressure_deficit: PressureUnit? = null,
    val soil_temperature_0_to_7cm: TemperatureUnit? = null,
    val soil_temperature_7_to_28cm: TemperatureUnit? = null,
    val soil_temperature_28_to_100cm: TemperatureUnit? = null,
    val soil_temperature_100_to_255cm: TemperatureUnit? = null,
    val soil_moisture_0_to_7cm: String? = null,
    val soil_moisture_7_to_28cm: String? = null,
    val soil_moisture_28_to_100cm: String? = null,
    val soil_moisture_100_to_255cm: String? = null,
)
