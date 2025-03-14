package com.openmeteo.api.common.units

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Units(vararg val alias: @Contextual Any) {
    Unknown,

    @SerialName("unixtime")
    UnixTime,

    @SerialName("")
    Dimensionless,

    @SerialName("wmo code")
    WeatherCode,

    @SerialName("%")
    Percentage,

    @SerialName("°")
    DecimalDegrees,

    @SerialName("°C")
    Celsius(TemperatureUnit.Celsius),

    @SerialName("°F")
    Fahrenheit(TemperatureUnit.Fahrenheit),

    @SerialName("mm")
    Millimeters(PrecipitationUnit.Millimeters),

    @SerialName("inch")
    Inches(PrecipitationUnit.Inches),

    @SerialName("km/h")
    KilometresPerHour(WindSpeedUnit.KilometresPerHour),

    @SerialName("m/s")
    MetresPerSeconds(WindSpeedUnit.MetresPerSeconds),

    @SerialName("mph")
    MilesPerHour(WindSpeedUnit.MilesPerHour),

    @SerialName("kn")
    Knots(WindSpeedUnit.Knots),

    @SerialName("cm")
    Centimeters,

    @SerialName("m")
    Meters(LengthUnit.Metric),

    @SerialName("ft")
    Feet(LengthUnit.Imperial),

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

    @SerialName("J/kg")
    JoulesPerKilogram,

    @SerialName("m³/m³")
    CubeMetersPerCubeMeter,

    @SerialName("MJ/m²")
    MegajoulesPerSquareMeter,

    @SerialName("gpm")
    GeoPotentialMeters,

    @SerialName("s⁻¹")
    SecondsInverse,

    @SerialName("g/kg")
    GramsPerKilogram,

    @SerialName("USAQI")
    UnitedStatesAirQualityIndex,

    @SerialName("EAQI")
    EuropeanAirQualityIndex,

    @SerialName("m³/s")
    CubeMetersPerSecond,
    ;

    companion object {
        fun from(that: Any) = values()
            .firstOrNull { it.aliasOf(that) } ?: Unknown
    }

    fun aliasOf(other: Any) =
        other in alias
}
