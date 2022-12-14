package com.openmeteo.api.airquality

import com.openmeteo.api.common.query.QueryHourly.Options
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AirQualityHourly : Options {
    @SerialName("time")
    Time,
    @SerialName("pm10")
    Pm10,
    @SerialName("pm2_5")
    Pm25,
    @SerialName("carbon_monoxide")
    CarbonMonoxide,
    @SerialName("nitrogen_dioxide")
    NitrogenDioxide,
    @SerialName("sulphur_dioxide")
    SulphurDioxide,
    @SerialName("ozone")
    Ozone,
    @SerialName("aerosol_optical_depth")
    AerosolOpticalDepth,
    @SerialName("dust")
    Dust,
    @SerialName("uv_index")
    UvIndex,
    @SerialName("uv_index_clear_sky")
    UvIndexClearSky,
    @SerialName("alder_pollen")
    AlderPollen,
    @SerialName("birch_pollen")
    BirchPollen,
    @SerialName("grass_pollen")
    GrassPollen,
    @SerialName("mugwort_pollen")
    MugwortPollen,
    @SerialName("olive_pollen")
    OlivePollen,
    @SerialName("ragweed_pollen")
    RagweedPollen,
}
