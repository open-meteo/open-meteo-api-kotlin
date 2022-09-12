package com.openmeteo.apix.marine

import com.openmeteo.apix.common.response.ResponseDaily
import com.openmeteo.apix.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DailyValues(
    override val time: Array<Time>,
    @SerialName("wave_height_max")
    val waveHeightMax: Array<Float?>? = null,
    @SerialName("wave_direction_dominant")
    val waveDirectionDominant: Array<Float?>? = null,
    @SerialName("wave_period_max")
    val wavePeriodMax: Array<Float?>? = null,
    @SerialName("wind_wave_height_max")
    val windWaveHeightMax: Array<Float?>? = null,
    @SerialName("wind_wave_direction_dominant")
    val windWaveDirectionDominant: Array<Float?>? = null,
    @SerialName("wind_wave_period_max")
    val windWavePeriodMax: Array<Float?>? = null,
    @SerialName("wind_wave_peak_period_max")
    val windWavePeakPeriodMax: Array<Float?>? = null,
    @SerialName("swell_wave_height_max")
    val swellWaveHeightMax: Array<Float?>? = null,
    @SerialName("swell_wave_direction_dominant")
    val swellWaveDirectionDominant: Array<Float?>? = null,
    @SerialName("swell_wave_period_max")
    val swellWavePeriodMax: Array<Float?>? = null,
    @SerialName("swell_wave_peak_period_max")
    val swellWavePeakPeriodMax: Array<Float?>? = null,
) : ResponseDaily.Values
