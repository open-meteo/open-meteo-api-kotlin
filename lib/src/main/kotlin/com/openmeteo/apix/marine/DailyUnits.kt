package com.openmeteo.apix.marine

import com.openmeteo.apix.common.response.ResponseDaily
import com.openmeteo.apix.common.time.TimeFormat
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DailyUnits(
    override val time: TimeFormat,
    @SerialName("wave_height_max")
    val waveHeightMax: Unit? = null,
    @SerialName("wave_direction_dominant")
    val waveDirectionDominant: Unit? = null,
    @SerialName("wave_period_max")
    val wavePeriodMax: Unit? = null,
    @SerialName("wind_wave_height_max")
    val windWaveHeightMax: Unit? = null,
    @SerialName("wind_wave_direction_dominant")
    val windWaveDirectionDominant: Unit? = null,
    @SerialName("wind_wave_period_max")
    val windWavePeriodMax: Unit? = null,
    @SerialName("wind_wave_peak_period_max")
    val windWavePeakPeriodMax: Unit? = null,
    @SerialName("swell_wave_height_max")
    val swellWaveHeightMax: Unit? = null,
    @SerialName("swell_wave_direction_dominant")
    val swellWaveDirectionDominant: Unit? = null,
    @SerialName("swell_wave_period_max")
    val swellWavePeriodMax: Unit? = null,
    @SerialName("swell_wave_peak_period_max")
    val swellWavePeakPeriodMax: Unit? = null,
) : ResponseDaily.Units
