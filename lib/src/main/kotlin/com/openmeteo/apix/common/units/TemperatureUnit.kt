package com.openmeteo.apix.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TemperatureUnit {
    @SerialName("°C")
    Celsius,

    @SerialName("°F")
    Fahrenheit,
}
