package com.openmeteo.api.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The unit that should be used for temperatures
 */
@Serializable
enum class TemperatureUnit {
    @SerialName("°C")
    celsius,

    @SerialName("°F")
    fahrenheit,
}