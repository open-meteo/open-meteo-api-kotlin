package com.openmeteo.api.airquality.serials

import com.openmeteo.api.common.serials.TimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Hourly(
    val time: Array<@Serializable(with = TimeSerializer::class) Date>,
    val pm10: FloatArray? = null,
    val pm2_5: FloatArray? = null,
    @SerialName("carbon_monoxide")
    val carbonMonoxide: FloatArray? = null,
    @SerialName("nitrogen_dioxide")
    val nitrogenDioxide: FloatArray? = null,
    @SerialName("sulphur_dioxide")
    val sulphurDioxide: FloatArray? = null,
    val ozone: FloatArray? = null,
    @SerialName("aerosol_optical_depth")
    val aerosolOpticalDepth: FloatArray? = null,
    val dust: FloatArray? = null,
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
)

