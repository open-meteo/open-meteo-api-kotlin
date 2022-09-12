package com.openmeteo.apix.marine

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyValues(
    override val time: Array<Time>,
    @SerialName("wave_height")
    val waveHeight: Array<Float?>? = null,
    @SerialName("wave_direction")
    val waveDirection: Array<Float?>? = null,
    @SerialName("wave_period")
    val wavePeriod: Array<Float?>? = null,
    @SerialName("wind_wave_height")
    val windWaveHeight: Array<Float?>? = null,
    @SerialName("wind_wave_direction")
    val windWaveDirection: Array<Float?>? = null,
    @SerialName("wind_wave_period")
    val windWavePeriod: Array<Float?>? = null,
    @SerialName("wind_wave_peak_period")
    val windWavePeakPeriod: Array<Float?>? = null,
    @SerialName("swell_wave_height")
    val swellWaveHeight: Array<Float?>? = null,
    @SerialName("swell_wave_direction")
    val swellWaveDirection: Array<Float?>? = null,
    @SerialName("swell_wave_period")
    val swellWavePeriod: Array<Float?>? = null,
    @SerialName("swell_wave_peak_period")
    val swellWavePeakPeriod: Array<Float?>? = null,
) : ResponseHourly.Values
