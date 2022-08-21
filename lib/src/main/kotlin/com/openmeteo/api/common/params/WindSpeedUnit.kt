package com.openmeteo.api.common.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The unit that should be used for wind speeds
 */
@Serializable
enum class WindSpeedUnit {
    @SerialName("km/h")
    kmh,

    @SerialName("m/s")
    ms,
    mph,

    @SerialName("knots")
    kn,
}