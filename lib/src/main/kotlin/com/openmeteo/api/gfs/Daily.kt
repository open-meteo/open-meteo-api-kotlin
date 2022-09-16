package com.openmeteo.api.gfs

import com.openmeteo.api.common.query.QueryDaily
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Daily : QueryDaily.Options {
    @SerialName("weathercode")
    Weathercode,
    @SerialName("temperature_2m_max")
    Temperature2mMax,
    @SerialName("temperature_2m_min")
    Temperature2mMin,
    @SerialName("apparent_temperature_max")
    ApparentTemperatureMax,
    @SerialName("apparent_temperature_min")
    ApparentTemperatureMin,
    @SerialName("sunrise")
    Sunrise,
    @SerialName("sunset")
    Sunset,
    @SerialName("precipitation_sum")
    PrecipitationSum,
    @SerialName("rain_sum")
    RainSum,
    @SerialName("showers_sum")
    ShowersSum,
    @SerialName("snowfall_sum")
    SnowfallSum,
    @SerialName("precipitation_hours")
    PrecipitationHours,
    @SerialName("windspeed_10m_max")
    Windspeed10mMax,
    @SerialName("windgusts_10m_max")
    Windgusts10mMax,
    @SerialName("winddirection_10m_dominant")
    Winddirection10mDominant,
    @SerialName("shortwave_radiation_sum")
    ShortwaveRadiationSum,
    @SerialName("et0_fao_evapotranspiration")
    Et0FaoEvapotranspiration,
}
