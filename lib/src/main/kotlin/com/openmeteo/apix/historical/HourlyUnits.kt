package com.openmeteo.apix.historical

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
    @SerialName("pressure_msl")
    val pressureMsl: Unit? = null,
    @SerialName("surface_pressure")
    val surfacePressure: Unit? = null,
    @SerialName("precipitation")
    val precipitation: Unit? = null,
    @SerialName("rain")
    val rain: Unit? = null,
    @SerialName("snowfall")
    val snowfall: Unit? = null,
    @SerialName("cloudcover")
    val cloudcover: Unit? = null,
    @SerialName("cloudcover_low")
    val cloudcoverLow: Unit? = null,
    @SerialName("cloudcover_mid")
    val cloudcoverMid: Unit? = null,
    @SerialName("cloudcover_high")
    val cloudcoverHigh: Unit? = null,
    @SerialName("shortwave_radiation")
    val shortwaveRadiation: Unit? = null,
    @SerialName("direct_radiation")
    val directRadiation: Unit? = null,
    @SerialName("diffuse_radiation")
    val diffuseRadiation: Unit? = null,
    @SerialName("direct_normal_irradiance")
    val directNormalIrradiance: Unit? = null,
    @SerialName("windspeed_10m")
    val windspeed10m: Unit? = null,
    @SerialName("windspeed_100m")
    val windspeed100m: Unit? = null,
    @SerialName("winddirection_10m")
    val winddirection10m: Unit? = null,
    @SerialName("winddirection_100m")
    val winddirection100m: Unit? = null,
    @SerialName("windgusts_10m")
    val windgusts10m: Unit? = null,
    @SerialName("et0_fao_evapotranspiration")
    val et0FaoEvapotranspiration: Unit? = null,
    @SerialName("vapor_pressure_deficit")
    val vaporPressureDeficit: Unit? = null,
    @SerialName("soil_temperature_0_to_7cm")
    val soilTemperature0To7cm: Unit? = null,
    @SerialName("soil_temperature_7_to_28cm")
    val soilTemperature7To28cm: Unit? = null,
    @SerialName("soil_temperature_28_to_100cm")
    val soilTemperature28To100cm: Unit? = null,
    @SerialName("soil_temperature_100_to_255cm")
    val soilTemperature100To255cm: Unit? = null,
    @SerialName("soil_moisture_0_to_7cm")
    val soilMoisture0To7cm: Unit? = null,
    @SerialName("soil_moisture_7_to_28cm")
    val soilMoisture7To28cm: Unit? = null,
    @SerialName("soil_moisture_28_to_100cm")
    val soilMoisture28To100cm: Unit? = null,
    @SerialName("soil_moisture_100_to_255cm")
    val soilMoisture100To255cm: Unit? = null,
) : ResponseHourly.Units
