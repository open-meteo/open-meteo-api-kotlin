package com.openmeteo.apix.historical

import com.openmeteo.apix.common.query.QueryHourly
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class HourlyOptions : QueryHourly.Options {
    @SerialName("temperature_2m")
    Temperature2m,
    @SerialName("relativehumidity_2m")
    Relativehumidity2m,
    @SerialName("dewpoint_2m")
    Dewpoint2m,
    @SerialName("apparent_temperature")
    ApparentTemperature,
    @SerialName("pressure_msl")
    PressureMsl,
    @SerialName("surface_pressure")
    SurfacePressure,
    @SerialName("precipitation")
    Precipitation,
    @SerialName("rain")
    Rain,
    @SerialName("snowfall")
    Snowfall,
    @SerialName("cloudcover")
    Cloudcover,
    @SerialName("cloudcover_low")
    CloudcoverLow,
    @SerialName("cloudcover_mid")
    CloudcoverMid,
    @SerialName("cloudcover_high")
    CloudcoverHigh,
    @SerialName("shortwave_radiation")
    ShortwaveRadiation,
    @SerialName("direct_radiation")
    DirectRadiation,
    @SerialName("diffuse_radiation")
    DiffuseRadiation,
    @SerialName("direct_normal_irradiance")
    DirectNormalIrradiance,
    @SerialName("windspeed_10m")
    Windspeed10m,
    @SerialName("windspeed_100m")
    Windspeed100m,
    @SerialName("winddirection_10m")
    Winddirection10m,
    @SerialName("winddirection_100m")
    Winddirection100m,
    @SerialName("windgusts_10m")
    Windgusts10m,
    @SerialName("et0_fao_evapotranspiration")
    Et0FaoEvapotranspiration,
    @SerialName("vapor_pressure_deficit")
    VaporPressureDeficit,
    @SerialName("soil_temperature_0_to_7cm")
    SoilTemperature0To7cm,
    @SerialName("soil_temperature_7_to_28cm")
    SoilTemperature7To28cm,
    @SerialName("soil_temperature_28_to_100cm")
    SoilTemperature28To100cm,
    @SerialName("soil_temperature_100_to_255cm")
    SoilTemperature100To255cm,
    @SerialName("soil_moisture_0_to_7cm")
    SoilMoisture0To7cm,
    @SerialName("soil_moisture_7_to_28cm")
    SoilMoisture7To28cm,
    @SerialName("soil_moisture_28_to_100cm")
    SoilMoisture28To100cm,
    @SerialName("soil_moisture_100_to_255cm")
    SoilMoisture100To255cm,
}

