package com.openmeteo.api.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WindSpeedUnit {
    @SerialName("kmh")
    KilometresPerHour,

    @SerialName("ms")
    MetresPerSeconds,

    @SerialName("mph")
    MilesPerHour,

    @SerialName("kn")
    Knots,
}
