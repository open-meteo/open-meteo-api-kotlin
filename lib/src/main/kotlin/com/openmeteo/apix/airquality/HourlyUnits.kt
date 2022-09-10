package com.openmeteo.apix.airquality

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.TimeFormat
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    override val time: TimeFormat,
    @SerialName("pm10")
    val pm10: Unit? = null,
    @SerialName("pm2_5")
    val pm25: Unit? = null,
    @SerialName("carbon_monoxide")
    val carbonMonoxide: Unit? = null,
    @SerialName("nitrogen_dioxide")
    val nitrogenDioxide: Unit? = null,
    @SerialName("sulphur_dioxide")
    val sulphurDioxide: Unit? = null,
    @SerialName("ozone")
    val ozone: Unit? = null,
    @SerialName("aerosol_optical_depth")
    val aerosolOpticalDepth: Unit? = null,
    @SerialName("dust")
    val dust: Unit? = null,
    @SerialName("uv_index")
    val uvIndex: Unit? = null,
    @SerialName("uv_index_clear_sky")
    val uvIndexClearSky: Unit? = null,
    @SerialName("alder_pollen")
    val alderPollen: Unit? = null,
    @SerialName("birch_pollen")
    val birchPollen: Unit? = null,
    @SerialName("grass_pollen")
    val grassPollen: Unit? = null,
    @SerialName("mugwort_pollen")
    val mugwortPollen: Unit? = null,
    @SerialName("olive_pollen")
    val olivePollen: Unit? = null,
    @SerialName("ragweed_pollen")
    val ragweedPollen: Unit? = null,
) : ResponseHourly.Units
