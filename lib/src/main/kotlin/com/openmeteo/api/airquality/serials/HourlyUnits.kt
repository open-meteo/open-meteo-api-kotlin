package com.openmeteo.api.airquality.serials

import com.openmeteo.api.common.params.TimeFormat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    val time: TimeFormat,
    val pm10: String? = null,
    val pm2_5: String? = null,
    @SerialName("carbon_monoxide")
    val carbonMonoxide: String? = null,
    @SerialName("nitrogen_dioxide")
    val nitrogenDioxide: String? = null,
    @SerialName("sulphur_dioxide")
    val sulphurDioxide: String? = null,
    val ozone: String? = null,
    @SerialName("aerosol_optical_depth")
    val aerosolOpticalDepth: String? = null,
    val dust: String? = null,
    @SerialName("uv_index")
    val uvIndex: String? = null,
    @SerialName("uv_index_clear_sky")
    val uvIndexClearSky: String? = null,
    @SerialName("alder_pollen")
    val alderPollen: String? = null,
    @SerialName("birch_pollen")
    val birchPollen: String? = null,
    @SerialName("grass_pollen")
    val grassPollen: String? = null,
    @SerialName("mugwort_pollen")
    val mugwortPollen: String? = null,
    @SerialName("olive_pollen")
    val olivePollen: String? = null,
    @SerialName("ragweed_pollen")
    val ragweedPollen: String? = null,
)

