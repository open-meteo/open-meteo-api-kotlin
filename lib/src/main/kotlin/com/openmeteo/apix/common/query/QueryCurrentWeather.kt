package com.openmeteo.apix.common.query

import kotlinx.serialization.SerialName

interface QueryCurrentWeather : Query {
    @SerialName("current_weather")
    val currentWeather: Boolean?
}
