package com.openmeteo.apix.historical

import com.openmeteo.apix.common.query.QueryDaily
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Daily : QueryDaily.Options {
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
    @SerialName("shortwave_radiation_sum")
    ShortwaveRadiationSum,
    @SerialName("precipitation_sum")
    PrecipitationSum,
    @SerialName("rain_sum")
    RainSum,
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
    @SerialName("et0_fao_evapotranspiration")
    Et0FaoEvapotranspiration,
}
