package com.openmeteo.api.historical.serials

import com.openmeteo.api.common.params.TemperatureUnit
import com.openmeteo.api.common.params.TimeFormat
import kotlinx.serialization.Serializable

@Serializable
class DailyUnits(
    val time: TimeFormat,
    val temperature_2m_max: TemperatureUnit? = null,
    val temperature_2m_min: TemperatureUnit? = null,
    val apparent_temperature_max: TemperatureUnit? = null,
    val apparent_temperature_min: TemperatureUnit? = null,
    val precipitation_sum: String? = null,
    val rain_sum: String? = null,
    val snowfall_sum: String? = null,
    val precipitation_hours: String? = null,
    val sunrise: TimeFormat? = null,
    val sunset: TimeFormat? = null,
    val windspeed_10m_max: String? = null,
    val windgusts_10m_max: String? = null,
    val winddirection_10m_dominant: String? = null,
    val shortwave_radiation_sum: String? = null,
    val et0_fao_evapotranspiration: String? = null,
)
