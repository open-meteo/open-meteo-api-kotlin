package com.openmeteo.api.airquality

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Domains {
    @SerialName("auto")
    Auto,

    @SerialName("cams_europe")
    CamsEurope,

    @SerialName("cams_global")
    CamsGlobal,
}
