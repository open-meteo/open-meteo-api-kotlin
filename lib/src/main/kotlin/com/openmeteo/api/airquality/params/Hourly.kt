package com.openmeteo.api.airquality.params

enum class Hourly {
    pm10,
    pm2_5,
    carbon_monoxide,
    nitrogen_dioxide,
    sulphur_dioxide,
    ozone,
    aerosol_optical_depth,
    dust,
    uv_index,
    uv_index_clear_sky,
    alder_pollen,
    birch_pollen,
    grass_pollen,
    mugwort_pollen,
    olive_pollen,
    ragweed_pollen;
    object Scope {
        val pm10 = Hourly.pm10
        val pm2_5 = Hourly.pm2_5
        val carbonMonoxide = carbon_monoxide
        val nitrogenDioxide = nitrogen_dioxide
        val sulphurDioxide = sulphur_dioxide
        val ozone = Hourly.ozone
        val aerosolOpticalDepth = aerosol_optical_depth
        val dust = Hourly.dust
        val uvIndex = uv_index
        val uvIndexClearSky = uv_index_clear_sky
        val alderPollen = alder_pollen
        val birchPollen = birch_pollen
        val grassPollen = grass_pollen
        val mugwortPollen = mugwort_pollen
        val olivePollen = olive_pollen
        val ragweedPollen = ragweed_pollen
    }
}