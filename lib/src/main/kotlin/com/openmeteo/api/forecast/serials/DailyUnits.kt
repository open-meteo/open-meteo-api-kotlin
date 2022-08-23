package com.openmeteo.api.forecast.serials

import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.time.TimeFormat
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.Serializable

@Serializable
class DailyUnits(
    val time: TimeFormat,
    val temperature_2m_max: TemperatureUnit? = null,
    val temperature_2m_min: TemperatureUnit? = null,
    val apparent_temperature_max: TemperatureUnit? = null,
    val apparent_temperature_min: TemperatureUnit? = null,
    val precipitation_sum: PrecipitationUnit? = null,
    val rain_sum: PrecipitationUnit? = null,
    val showers_sum: PrecipitationUnit? = null,
    val snowfall_sum: PrecipitationUnit? = null,
    val precipitation_hours: String? = null,
    val weathercode: String? = null,
    val sunrise: TimeFormat? = null,
    val sunset: TimeFormat? = null,
    val windspeed_10m_max: WindSpeedUnit? = null,
    val windgusts_10m_max: WindSpeedUnit? = null,
    val winddirection_10m_dominant: String? = null,
    val shortwave_radiation_sum: String? = null,
    val et0_fao_evapotranspiration: String? = null,
)
