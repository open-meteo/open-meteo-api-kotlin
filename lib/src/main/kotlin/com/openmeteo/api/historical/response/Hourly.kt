package com.openmeteo.api.historical.response

import com.openmeteo.api.common.time.Time
import kotlinx.serialization.Serializable

@Serializable
class Hourly(
    val time: Array<Time>,
    val temperature_2m: Array<Float?>? = null,
    val relativehumidity_2m: Array<Float?>? = null,
    val dewpoint_2m: Array<Float?>? = null,
    val apparent_temperature: Array<Float?>? = null,
    val pressure_msl: Array<Float?>? = null,
    val surface_pressure: Array<Float?>? = null,
    val precipitation: Array<Float?>? = null,
    val rain: Array<Float?>? = null,
    val snowfall: Array<Float?>? = null,
    val cloudcover: Array<Float?>? = null,
    val cloudcover_low: Array<Float?>? = null,
    val cloudcover_mid: Array<Float?>? = null,
    val cloudcover_high: Array<Float?>? = null,
    val shortwave_radiation: Array<Float?>? = null,
    val direct_radiation: Array<Float?>? = null,
    val direct_normal_irradiance: Array<Float?>? = null,
    val diffuse_radiation: Array<Float?>? = null,
    val windspeed_10m: Array<Float?>? = null,
    val windspeed_100m: Array<Float?>? = null,
    val winddirection_10m: Array<Float?>? = null,
    val winddirection_100m: Array<Float?>? = null,
    val windgusts_10m: Array<Float?>? = null,
    val et0_fao_evapotranspiration: Array<Float?>? = null,
    val vapor_pressure_deficit: Array<Float?>? = null,
    val soil_temperature_0_to_7cm: Array<Float?>? = null,
    val soil_temperature_7_to_28cm: Array<Float?>? = null,
    val soil_temperature_28_to_100cm: Array<Float?>? = null,
    val soil_temperature_100_to_255cm: Array<Float?>? = null,
    val soil_moisture_0_to_7cm: Array<Float?>? = null,
    val soil_moisture_7_to_28cm: Array<Float?>? = null,
    val soil_moisture_28_to_100cm: Array<Float?>? = null,
    val soil_moisture_100_to_255cm: Array<Float?>? = null,
)
