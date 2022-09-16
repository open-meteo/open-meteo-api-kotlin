package com.openmeteo.apix.marine

import com.openmeteo.apix.common.query.QueryDaily
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Daily : QueryDaily.Options {
    @SerialName("wave_height_max")
    WaveHeightMax,
    @SerialName("wave_direction_dominant")
    WaveDirectionDominant,
    @SerialName("wave_period_max")
    WavePeriodMax,
    @SerialName("wind_wave_height_max")
    WindWaveHeightMax,
    @SerialName("wind_wave_direction_dominant")
    WindWaveDirectionDominant,
    @SerialName("wind_wave_period_max")
    WindWavePeriodMax,
    @SerialName("wind_wave_peak_period_max")
    WindWavePeakPeriodMax,
    @SerialName("swell_wave_height_max")
    SwellWaveHeightMax,
    @SerialName("swell_wave_direction_dominant")
    SwellWaveDirectionDominant,
    @SerialName("swell_wave_period_max")
    SwellWavePeriodMax,
    @SerialName("swell_wave_peak_period_max")
    SwellWavePeakPeriodMax,
}
