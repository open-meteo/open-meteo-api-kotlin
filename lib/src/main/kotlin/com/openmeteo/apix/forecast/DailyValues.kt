package com.openmeteo.apix.forecast

import com.openmeteo.apix.common.response.ResponseDaily
import com.openmeteo.apix.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DailyValues(
    override val time: Array<Time>,
    @SerialName("weathercode")
    val weathercode: Array<Float?>? = null,
    @SerialName("temperature_2m_max")
    val temperature2mMax: Array<Float?>? = null,
    @SerialName("temperature_2m_min")
    val temperature2mMin: Array<Float?>? = null,
    @SerialName("apparent_temperature_max")
    val apparentTemperatureMax: Array<Float?>? = null,
    @SerialName("apparent_temperature_min")
    val apparentTemperatureMin: Array<Float?>? = null,
    @SerialName("sunrise")
    val sunrise: Array<Time?>? = null,
    @SerialName("sunset")
    val sunset: Array<Time?>? = null,
    @SerialName("precipitation_sum")
    val precipitationSum: Array<Float?>? = null,
    @SerialName("rain_sum")
    val rainSum: Array<Float?>? = null,
    @SerialName("showers_sum")
    val showersSum: Array<Float?>? = null,
    @SerialName("snowfall_sum")
    val snowfallSum: Array<Float?>? = null,
    @SerialName("precipitation_hours")
    val precipitationHours: Array<Float?>? = null,
    @SerialName("windspeed_10m_max")
    val windspeed10mMax: Array<Float?>? = null,
    @SerialName("windgusts_10m_max")
    val windgusts10mMax: Array<Float?>? = null,
    @SerialName("winddirection_10m_dominant")
    val winddirection10mDominant: Array<Float?>? = null,
    @SerialName("shortwave_radiation_sum")
    val shortwaveRadiationSum: Array<Float?>? = null,
    @SerialName("et0_fao_evapotranspiration")
    val et0FaoEvapotranspiration: Array<Float?>? = null,
) : ResponseDaily.Values
