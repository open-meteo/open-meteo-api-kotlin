package com.openmeteo.api.marine.serials

import com.openmeteo.api.common.params.TimeFormat
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    val time: TimeFormat,
    val wave_height: String? = null,
    val wind_wave_height: String? = null,
    val swell_wave_height: String? = null,
    val wave_direction: String? = null,
    val wind_wave_direction: String? = null,
    val swell_wave_direction: String? = null,
    val wave_period: String? = null,
    val wind_wave_period: String? = null,
    val swell_wave_period: String? = null,
    val wind_wave_peak_period: String? = null,
    val swell_wave_peak_period: String? = null,
)
