package com.openmeteo.sdk.common.units

import kotlinx.serialization.SerialName

enum class LengthUnit {
    @SerialName("metric")
    Metric,

    @SerialName("imperial")
    Imperial,
}
