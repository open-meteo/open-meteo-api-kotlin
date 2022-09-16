package com.openmeteo.apix.common.units

import com.openmeteo.apix.common.time.TimeFormat
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Unit(vararg val alias: @Contextual Any) {
    @SerialName("iso8601")
    Iso8601(TimeFormat.Iso8601),

    @SerialName("unixtime")
    UnixTime(TimeFormat.UnixTime),

    @SerialName("")
    Dimensionless,

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

    @SerialName("knots")
    Knots(WindSpeedUnit.Knots),

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
    Kilopascals;

    fun aliasOf(other: Any) =
        other in alias
}
