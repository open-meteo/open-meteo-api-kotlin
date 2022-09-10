package com.openmeteo.apix.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WindSpeedUnit {
    @SerialName("km/h")
    KilometresPerHour,

    @SerialName("m/s")
    MetresPerSeconds,

    @SerialName("mph")
    MilesPerHour,

    @SerialName("knots")
    Knots,
}
