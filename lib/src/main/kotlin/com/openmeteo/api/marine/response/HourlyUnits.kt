package com.openmeteo.api.marine.response

import com.openmeteo.api.common.time.TimeFormat
import com.openmeteo.api.common.units.TimeUnit
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
    val wave_period: TimeUnit? = null,
    val wind_wave_period: TimeUnit? = null,
    val swell_wave_period: TimeUnit? = null,
    val wind_wave_peak_period: TimeUnit? = null,
    val swell_wave_peak_period: TimeUnit? = null,
)
