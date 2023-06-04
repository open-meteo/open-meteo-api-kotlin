package com.openmeteo.api.common

import com.openmeteo.api.common.time.Time
import com.openmeteo.api.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Response {

    /**
     * Location-based resource.
     */
    interface Coordinate : com.openmeteo.api.common.Coordinate, Response

    /**
     * Location-based resource that contains timestamps.
     */
    interface TimeZone : Response {
        val utcOffsetSeconds: Int
        val timezone: com.openmeteo.api.common.time.Timezone
        val timezoneAbbreviation: String
    }

    /**
     * Resource which generation is timed.
     */
    interface GenerationTimed : Response {
        val generationtimeMs: Float
    }

    /**
     * Resources that may have daily data entries.
     */
    interface Daily : Response {
        val dailyUnits: Map<String, Unit>
        val dailyValues: Map<String, Array<Double?>>
    }

    /**
     * Resources that may have hourly data entries.
     */
    interface Hourly : Response {
        val hourlyUnits: Map<String, Unit>
        val hourlyValues: Map<String, Array<Double?>>
    }

    /**
     * Resource which may contain the current weather status.
     */
    interface CurrentWeather : Response {

        /**
         * The current weather.
         */
        val currentWeather: CurrentWeather?

        @Serializable
        open class CurrentWeather(
            val time: Time,
            val temperature: Float,
            @SerialName("windspeed")
            val windSpeed: Float,
            @SerialName("winddirection")
            val windDirection: Float,
            @SerialName("weathercode")
            val weatherCode: WeatherCode,
            /**
             * Whether the time step has daylight
             */
            @Serializable(with = IntAsBoolean::class)
            val isDay: Boolean,
        )
    }

    interface Elevation {
        val elevation: Float
    }
}
