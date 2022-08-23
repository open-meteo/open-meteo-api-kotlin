package com.openmeteo.api.marine.response

import com.openmeteo.api.common.time.TimeFormat
import com.openmeteo.api.common.units.TimeUnit
import kotlinx.serialization.Serializable

@Serializable
class DailyUnits(
    val time: TimeFormat,
    val wave_height_max: String? = null,
    val wind_wave_height_max: String? = null,
    val swell_wave_height_max: String? = null,
    val wave_direction_max: String? = null,
    val wind_wave_direction_max: String? = null,
    val swell_wave_direction_max: String? = null,
    val wave_period_max: TimeUnit? = null,
    val wind_wave_period_max: TimeUnit? = null,
    val swell_wave_period_max: TimeUnit? = null,
    val wind_wave_peak_period_max: TimeUnit? = null,
    val swell_wave_peak_period_max: TimeUnit? = null,
)
