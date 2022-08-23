package com.openmeteo.api.airquality.response

import com.openmeteo.api.common.time.Time
import kotlinx.serialization.Serializable

@Serializable
class Hourly(
    val time: Array<Time>,
    val pm10: FloatArray? = null,
    val pm2_5: FloatArray? = null,
    val carbon_monoxide: FloatArray? = null,
    val nitrogen_dioxide: FloatArray? = null,
    val sulphur_dioxide: FloatArray? = null,
    val ozone: FloatArray? = null,
    val aerosol_optical_depth: FloatArray? = null,
    val dust: FloatArray? = null,
    val uv_index: Array<Float?>? = null,
    val uv_index_clear_sky: Array<Float?>? = null,
    val alder_pollen: Array<Float?>? = null,
    val birch_pollen: Array<Float?>? = null,
    val grass_pollen: Array<Float?>? = null,
    val mugwort_pollen: Array<Float?>? = null,
    val olive_pollen: Array<Float?>? = null,
    val ragweed_pollen: Array<Float?>? = null,
)

