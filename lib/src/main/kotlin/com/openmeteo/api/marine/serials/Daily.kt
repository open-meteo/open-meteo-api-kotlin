package com.openmeteo.api.marine.serials

import com.openmeteo.api.common.time.Time
import kotlinx.serialization.Serializable

@Serializable
class Daily(
    val time: Array<Time>,
    val wave_height_max: Array<Float?>? = null,
    val wind_wave_height_max: Array<Float?>? = null,
    val swell_wave_height_max: Array<Float?>? = null,
    val wave_direction_max: Array<Float?>? = null,
    val wind_wave_direction_max: Array<Float?>? = null,
    val swell_wave_direction_max: Array<Float?>? = null,
    val wave_period_max: Array<Float?>? = null,
    val wind_wave_period_max: Array<Float?>? = null,
    val swell_wave_period_max: Array<Float?>? = null,
    val wind_wave_peak_period_max: Array<Float?>? = null,
    val swell_wave_peak_period_max: Array<Float?>? = null,
)
