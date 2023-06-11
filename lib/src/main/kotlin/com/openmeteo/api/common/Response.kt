package com.openmeteo.api.common

import com.openmeteo.api.common.units.Units

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
        val dailyUnits: Map<String, Units>
        val dailyValues: Map<String, Array<Double?>>
    }

    /**
     * Resources that may have hourly data entries.
     */
    interface Hourly : Response {
        val hourlyUnits: Map<String, Units>
        val hourlyValues: Map<String, Array<Double?>>
    }

    /**
     * Resource which may contain the current weather status.
     */
    interface CurrentWeather : Response {
        /**
         * The current weather.
         */
        val currentWeather: com.openmeteo.api.common.CurrentWeather?
    }

    /**
     * Elevation-based resource.
     */
    interface Elevation : com.openmeteo.api.common.Elevation {
        override val elevation: Float // not nullable, since it's always returned!
    }

}
