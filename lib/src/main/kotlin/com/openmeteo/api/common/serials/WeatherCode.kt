package com.openmeteo.api.common.serials

import kotlinx.serialization.Serializable

@Serializable(with = WeatherCodeSerializer::class)
enum class WeatherCode(val code: Short, val message: String) {
    CLEAR_SKY(0, "Clear sky"),
    MAINLY_CLEAR(1, "Mainly clear"),
    PARTLY_CLOUDY(2, "Partly cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    DEPOSITING_RIME_FOG(48, "Depositing rime fog"),
    DRIZZLE_LIGHT(51, "Light intensity drizzle"),
    DRIZZLE_MODERATE(53, "Moderate intensity drizzle"),
    DRIZZLE_DENSE(55, "Dense intensity drizzle"),
    FREEZING_DRIZZLE_LIGHT(56, "Light intensity freezing drizzle"),
    FREEZING_DRIZZLE_DENSE(57, "Dense intensity freezing drizzle"),
    RAIN_SLIGHT(61, "Slight intensity rain"),
    RAIN_MODERATE(63, "Moderate intensity rain"),
    RAIN_HEAVY(65, "Heavy intensity rain"),
    FREEZING_RAIN_LIGHT(66, "Light intensity freezing rain"),
    FREEZING_RAIN_HEAVY(67, "Dense intensity freezing rain"),
    SNOW_FALL_SLIGHT(71, "Slight intensity snow fall"),
    SNOW_FALL_MODERATE(73, "Moderate intensity snow fall"),
    SNOW_FALL_HEAVY(75, "Heavy intensity snow fall"),
    SNOW_GRAINS(77, "Snow grains"),
    RAIN_SHOWERS_SLIGHT(80, "Slight rain showers"),
    RAIN_SHOWERS_MODERATE(81, "Moderate rain showers"),
    RAIN_SHOWERS_VIOLENT(82, "Violent rain showers"),
    SNOW_SHOWERS_SLIGHT(85, "Slight snow showers"),
    SNOW_SHOWERS_HEAVY(86, "Heavy snow showers"),
    THUNDERSTORM_SLIGHT(95, "Slight or moderate thunderstorm"),
    THUNDERSTORM_SLIGHT_HAIL(96, "Thunderstorm with slight hail"),
    THUNDERSTORM_HEAVY_HAIL(99, "Thunderstorm with heavy hail"),
}