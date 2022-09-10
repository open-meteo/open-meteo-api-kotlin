package com.openmeteo.apix.marine

import com.openmeteo.apix.common.query.QueryHourly
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class HourlyOptions : QueryHourly.Options {
    @SerialName("wave_height")
    WaveHeight,
    @SerialName("wave_direction")
    WaveDirection,
    @SerialName("wave_period")
    WavePeriod,
    @SerialName("wind_wave_height")
    WindWaveHeight,
    @SerialName("wind_wave_direction")
    WindWaveDirection,
    @SerialName("wind_wave_period")
    WindWavePeriod,
    @SerialName("wind_wave_peak_period")
    WindWavePeakPeriod,
    @SerialName("swell_wave_height")
    SwellWaveHeight,
    @SerialName("swell_wave_direction")
    SwellWaveDirection,
    @SerialName("swell_wave_period")
    SwellWavePeriod,
    @SerialName("swell_wave_peak_period")
    SwellWavePeakPeriod,
}
