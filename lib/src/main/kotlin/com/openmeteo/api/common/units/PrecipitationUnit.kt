package com.openmeteo.api.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PrecipitationUnit {
    @SerialName("mm")
    Millimeters,

    @SerialName("inch")
    Inches,
}
