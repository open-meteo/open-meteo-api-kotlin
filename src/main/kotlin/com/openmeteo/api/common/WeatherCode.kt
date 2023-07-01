package com.openmeteo.api.common

import kotlinx.serialization.Serializable

/**
 * A list of "wmo code"s, following [the official docs](https://open-meteo.com/en/docs#api_form)
 */
@Serializable(with = WeatherCodeSerializer::class)
enum class WeatherCode(val code: Short, val message: String) {
    Unknown(-1, "Unknown"), // fallback
    Clear(0, "Clear"),
    MainlyClear(1, "Mostly clear"),
    PartlyCloudy(2, "Partly cloudy"),
    Overcast(3, "Cloudy"),
    Fog(45, "Fog"),
    DepositingRimeFog(48, "Freezing fog"),
    DrizzleLight(51, "Light drizzle"),
    DrizzleModerate(53, "Drizzle"),
    DrizzleDense(55, "Heavy drizzle"),
    FreezingDrizzleLight(56, "Light freezing drizzle"),
    FreezingDrizzleDense(57, "Freezing drizzle"),
    RainSlight(61, "Light rain"),
    RainModerate(63, "Heavy rain"),
    RainHeavy(65, "Heavy intensity rain"),
    FreezingRainLight(66, "Light freezing rain"),
    FreezingRainHeavy(67, "Freezing rain"),
    SnowFallSlight(71, "Light snow"),
    SnowFallModerate(73, "Snow"),
    SnowFallHeavy(75, "Heavy snow"),
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
