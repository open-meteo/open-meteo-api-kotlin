package com.openmeteo.api.common.weather

import kotlinx.serialization.Serializable

/**
 * A list of "wmo code"s, following [the official docs](https://open-meteo.com/en/docs#api_form)
 */
@Serializable(with = WeatherCodeSerializer::class)
enum class WeatherCode(val code: Short, val message: String) {
    Unknown(-1, "Unknown"), // fallback
    ClearSky(0, "Clear sky"),
    MainlyClear(1, "Mainly clear"),
    PartlyCloudy(2, "Partly cloudy"),
    Overcast(3, "Overcast"),
    Fog(45, "Fog"),
    DepositingRimeFog(48, "Depositing rime fog"),
    DrizzleLight(51, "Light intensity drizzle"),
    DrizzleModerate(53, "Moderate intensity drizzle"),
    DrizzleDense(55, "Dense intensity drizzle"),
    FreezingDrizzleLight(56, "Light intensity freezing drizzle"),
    FreezingDrizzleDense(57, "Dense intensity freezing drizzle"),
    RainSlight(61, "Slight intensity rain"),
    RainModerate(63, "Moderate intensity rain"),
    RainHeavy(65, "Heavy intensity rain"),
    FreezingRainLight(66, "Light intensity freezing rain"),
    FreezingRainHeavy(67, "Dense intensity freezing rain"),
    SnowFallSlight(71, "Slight intensity snow fall"),
    SnowFallModerate(73, "Moderate intensity snow fall"),
    SnowFallHeavy(75, "Heavy intensity snow fall"),
    SnowGrains(77, "Snow grains"),
    RainShowersSlight(80, "Slight rain showers"),
    RainShowersModerate(81, "Moderate rain showers"),
    RainShowersViolent(82, "Violent rain showers"),
    SnowShowersSlight(85, "Slight snow showers"),
    SnowShowersHeavy(86, "Heavy snow showers"),
    ThunderstormSlight(95, "Slight or moderate thunderstorm"),
    ThunderstormSlightHail(96, "Thunderstorm with slight hail"),
    ThunderstormHeavyHail(99, "Thunderstorm with heavy hail");

    override fun toString(): String = message

    companion object {
        fun from(code: Short) = values()
            .firstOrNull { it.code == code } ?: Unknown
        fun from(code: Int) = from(code.toShort())
        fun from(code: Double) = from(code.toInt())
    }
}
