package com.openmeteo.api.common.units

import kotlinx.serialization.SerialName

enum class LengthUnit {
    @SerialName("metric")
    Metric,

    @SerialName("imperial")
    Imperial,
}
