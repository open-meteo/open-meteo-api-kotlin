package com.openmeteo.sdk.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TemperatureUnit {
    @SerialName("celsius")
    Celsius,

    @SerialName("fahrenheit")
    Fahrenheit,
}
