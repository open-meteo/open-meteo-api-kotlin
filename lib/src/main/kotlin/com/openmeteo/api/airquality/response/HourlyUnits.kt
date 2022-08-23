package com.openmeteo.api.airquality.response

import com.openmeteo.api.common.time.TimeFormat
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    val time: TimeFormat,
    val pm10: String? = null,
    val pm2_5: String? = null,
    val carbon_monoxide: String? = null,
    val nitrogen_dioxide: String? = null,
    val sulphur_dioxide: String? = null,
    val ozone: String? = null,
    val aerosol_optical_depth: String? = null,
    val dust: String? = null,
    val uv_index: String? = null,
    val uv_index_clear_sky: String? = null,
    val alder_pollen: String? = null,
    val birch_pollen: String? = null,
    val grass_pollen: String? = null,
    val mugwort_pollen: String? = null,
    val olive_pollen: String? = null,
    val ragweed_pollen: String? = null,
)

