package com.openmeteo.apix.common.units

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Unit {
    @SerialName("")
    Dimensionless,

    @SerialName("%")
    Percentage,

    @SerialName("°")
    DecimalDegrees,

    @SerialName("cm")
    Centimeters,

    @SerialName("m")
    Meters,

    @SerialName("W/m²")
    WattPerSquareMeter,

    @SerialName("μg/m³")
    MicroGramsPerCubeMeter,

    @SerialName("grains/m³")
    GrainsPerCubeMeter,

    @SerialName("s")
    Seconds,

    @SerialName("h")
    Hours,

    @SerialName("hPa")
    Hectopascals,

    @SerialName("kPa")
    Kilopascals,
}
