package com.openmeteo.apix.airquality

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyValues(
    override val time: Array<Time>,
    @SerialName("pm10")
    val pm10: Array<Float?>? = null,
    @SerialName("pm2_5")
    val pm25: Array<Float?>? = null,
    @SerialName("carbon_monoxide")
    val carbonMonoxide: Array<Float?>? = null,
    @SerialName("nitrogen_dioxide")
    val nitrogenDioxide: Array<Float?>? = null,
    @SerialName("sulphur_dioxide")
    val sulphurDioxide: Array<Float?>? = null,
    @SerialName("ozone")
    val ozone: Array<Float?>? = null,
    @SerialName("aerosol_optical_depth")
    val aerosolOpticalDepth: Array<Float?>? = null,
    @SerialName("dust")
    val dust: Array<Float?>? = null,
    @SerialName("uv_index")
    val uvIndex: Array<Float?>? = null,
    @SerialName("uv_index_clear_sky")
    val uvIndexClearSky: Array<Float?>? = null,
    @SerialName("alder_pollen")
    val alderPollen: Array<Float?>? = null,
    @SerialName("birch_pollen")
    val birchPollen: Array<Float?>? = null,
    @SerialName("grass_pollen")
    val grassPollen: Array<Float?>? = null,
    @SerialName("mugwort_pollen")
    val mugwortPollen: Array<Float?>? = null,
    @SerialName("olive_pollen")
    val olivePollen: Array<Float?>? = null,
    @SerialName("ragweed_pollen")
    val ragweedPollen: Array<Float?>? = null,
) : ResponseHourly.Values
