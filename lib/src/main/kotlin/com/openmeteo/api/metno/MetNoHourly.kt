package com.openmeteo.api.metno

import com.openmeteo.api.common.query.QueryHourly.Options
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MetNoHourly : Options {
    @SerialName("time")
    Time,
    @SerialName("temperature_2m")
    Temperature2m,
    @SerialName("apparent_temperature")
    ApparentTemperature,
    @SerialName("dewpoint_2m")
    Dewpoint2m,
    @SerialName("relativehumidity_2m")
    Relativehumidity2m,
    @SerialName("cloudcover")
    Cloudcover,
    @SerialName("pressure_msl")
    PressureMsl,
    @SerialName("surface_pressure")
    SurfacePressure,
    @SerialName("precipitation")
    Precipitation,
    @SerialName("et0_fao_evapotranspiration")
    Et0FaoEvapotranspiration,
    @SerialName("vapor_pressure_deficit")
    VaporPressureDeficit,
    @SerialName("windspeed_10m")
    Windspeed10m,
    @SerialName("winddirection_10m")
    Winddirection10m,
    @SerialName("windgusts_10m")
    Windgusts10m,
    @SerialName("shortwave_radiation")
    ShortwaveRadiation,
    @SerialName("direct_radiation")
    DirectRadiation,
    @SerialName("diffuse_radiation")
    DiffuseRadiation,
    @SerialName("direct_normal_irradiance")
    DirectNormalIrradiance,
    @SerialName("terrestrial_radiation")
    TerrestrialRadiation,
    @SerialName("shortwave_radiation_instant")
    ShortwaveRadiationInstant,
    @SerialName("direct_radiation_instant")
    DirectRadiationInstant,
    @SerialName("diffuse_radiation_instant")
    DiffuseRadiationInstant,
    @SerialName("direct_normal_irradiance_instant")
    DirectNormalIrradianceInstant,
    @SerialName("terrestrial_radiation_instant")
    TerrestrialRadiationInstant,
}
