package com.openmeteo.api.marine.response

import com.openmeteo.api.common.time.Time
import kotlinx.serialization.Serializable

@Serializable
class Hourly(
    val time: Array<Time>,
    val wave_height: Array<Float?>? = null,
    val wind_wave_height: Array<Float?>? = null,
    val swell_wave_height: Array<Float?>? = null,
    val wave_direction: Array<Float?>? = null,
    val wind_wave_direction: Array<Float?>? = null,
    val swell_wave_direction: Array<Float?>? = null,
    val wave_period: Array<Float?>? = null,
    val wind_wave_period: Array<Float?>? = null,
    val swell_wave_period: Array<Float?>? = null,
    val wind_wave_peak_period: Array<Float?>? = null,
    val swell_wave_peak_period: Array<Float?>? = null,
)
