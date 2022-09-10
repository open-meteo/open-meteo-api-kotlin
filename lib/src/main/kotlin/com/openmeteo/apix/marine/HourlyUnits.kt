package com.openmeteo.apix.marine

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.TimeFormat
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    override val time: TimeFormat,
    @SerialName("wave_height")
    val waveHeight: Unit? = null,
    @SerialName("wave_direction")
    val waveDirection: Unit? = null,
    @SerialName("wave_period")
    val wavePeriod: Unit? = null,
    @SerialName("wind_wave_height")
    val windWaveHeight: Unit? = null,
    @SerialName("wind_wave_direction")
    val windWaveDirection: Unit? = null,
    @SerialName("wind_wave_period")
    val windWavePeriod: Unit? = null,
    @SerialName("wind_wave_peak_period")
    val windWavePeakPeriod: Unit? = null,
    @SerialName("swell_wave_height")
    val swellWaveHeight: Unit? = null,
    @SerialName("swell_wave_direction")
    val swellWaveDirection: Unit? = null,
    @SerialName("swell_wave_period")
    val swellWavePeriod: Unit? = null,
    @SerialName("swell_wave_peak_period")
    val swellWavePeakPeriod: Unit? = null,
) : ResponseHourly.Units
