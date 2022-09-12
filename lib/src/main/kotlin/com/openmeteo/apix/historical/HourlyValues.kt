package com.openmeteo.apix.historical

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
    @SerialName("pressure_msl")
    val pressureMsl: Array<Float?>? = null,
    @SerialName("surface_pressure")
    val surfacePressure: Array<Float?>? = null,
    @SerialName("precipitation")
    val precipitation: Array<Float?>? = null,
    @SerialName("rain")
    val rain: Array<Float?>? = null,
    @SerialName("snowfall")
    val snowfall: Array<Float?>? = null,
    @SerialName("cloudcover")
    val cloudcover: Array<Float?>? = null,
    @SerialName("cloudcover_low")
    val cloudcoverLow: Array<Float?>? = null,
    @SerialName("cloudcover_mid")
    val cloudcoverMid: Array<Float?>? = null,
    @SerialName("cloudcover_high")
    val cloudcoverHigh: Array<Float?>? = null,
    @SerialName("shortwave_radiation")
    val shortwaveRadiation: Array<Float?>? = null,
    @SerialName("direct_radiation")
    val directRadiation: Array<Float?>? = null,
    @SerialName("diffuse_radiation")
    val diffuseRadiation: Array<Float?>? = null,
    @SerialName("direct_normal_irradiance")
    val directNormalIrradiance: Array<Float?>? = null,
    @SerialName("windspeed_10m")
    val windspeed10m: Array<Float?>? = null,
    @SerialName("windspeed_100m")
    val windspeed100m: Array<Float?>? = null,
    @SerialName("winddirection_10m")
    val winddirection10m: Array<Float?>? = null,
    @SerialName("winddirection_100m")
    val winddirection100m: Array<Float?>? = null,
    @SerialName("windgusts_10m")
    val windgusts10m: Array<Float?>? = null,
    @SerialName("et0_fao_evapotranspiration")
    val et0FaoEvapotranspiration: Array<Float?>? = null,
    @SerialName("vapor_pressure_deficit")
    val vaporPressureDeficit: Array<Float?>? = null,
    @SerialName("soil_temperature_0_to_7cm")
    val soilTemperature0To7cm: Array<Float?>? = null,
    @SerialName("soil_temperature_7_to_28cm")
    val soilTemperature7To28cm: Array<Float?>? = null,
    @SerialName("soil_temperature_28_to_100cm")
    val soilTemperature28To100cm: Array<Float?>? = null,
    @SerialName("soil_temperature_100_to_255cm")
    val soilTemperature100To255cm: Array<Float?>? = null,
    @SerialName("soil_moisture_0_to_7cm")
    val soilMoisture0To7cm: Array<Float?>? = null,
    @SerialName("soil_moisture_7_to_28cm")
    val soilMoisture7To28cm: Array<Float?>? = null,
    @SerialName("soil_moisture_28_to_100cm")
    val soilMoisture28To100cm: Array<Float?>? = null,
    @SerialName("soil_moisture_100_to_255cm")
    val soilMoisture100To255cm: Array<Float?>? = null,
) : ResponseHourly.Values
