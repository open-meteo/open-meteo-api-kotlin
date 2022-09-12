package com.openmeteo.apix.historical

import com.openmeteo.apix.common.response.ResponseDaily
import com.openmeteo.apix.common.time.TimeFormat
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DailyUnits(
    override val time: TimeFormat,
    @SerialName("temperature_2m_max")
    val temperature2mMax: Unit? = null,
    @SerialName("temperature_2m_min")
    val temperature2mMin: Unit? = null,
    @SerialName("apparent_temperature_max")
    val apparentTemperatureMax: Unit? = null,
    @SerialName("apparent_temperature_min")
    val apparentTemperatureMin: Unit? = null,
    @SerialName("sunrise")
    val sunrise: TimeFormat? = null,
    @SerialName("sunset")
    val sunset: TimeFormat? = null,
    @SerialName("shortwave_radiation_sum")
    val shortwaveRadiationSum: Unit? = null,
    @SerialName("precipitation_sum")
    val precipitationSum: Unit? = null,
    @SerialName("rain_sum")
    val rainSum: Unit? = null,
    @SerialName("snowfall_sum")
    val snowfallSum: Unit? = null,
    @SerialName("precipitation_hours")
    val precipitationHours: Unit? = null,
    @SerialName("windspeed_10m_max")
    val windspeed10mMax: Unit? = null,
    @SerialName("windgusts_10m_max")
    val windgusts10mMax: Unit? = null,
    @SerialName("winddirection_10m_dominant")
    val winddirection10mDominant: Unit? = null,
    @SerialName("et0_fao_evapotranspiration")
    val et0FaoEvapotranspiration: Unit? = null,
) : ResponseDaily.Units
