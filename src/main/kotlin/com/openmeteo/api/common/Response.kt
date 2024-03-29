package com.openmeteo.api.common

import com.openmeteo.api.common.time.Time
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

    @RequiresOptIn(message = "This API is experimental. It may be changed in the future without notice.")
    @Retention(AnnotationRetention.BINARY)
    @Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
    annotation class ExperimentalGluedUnitTimeStepValues // Opt-in requirement annotation

    /**
     * A weather data "column" holding the values and their unit.
     */
    @ExperimentalGluedUnitTimeStepValues
    class UnitTimeStepValues(
        val unit: Units,
        val values: Map<Time, Double?>
    ) {

        companion object {

            /**
             * Glue units and values together into a map of keys (eg: temperature_2m) and unit/values
             */
            @ExperimentalGluedUnitTimeStepValues
            internal fun glue(
                units: Map<String, Units>,
                values: Map<String, Array<Double?>>,
            ): Map<String, UnitTimeStepValues> {
                // if we have too many keys in values drop the not common ones
                if (units.size < values.size)
                    return glue(units, values.filterKeys((units.keys intersect values.keys)::contains)) // retry
                // if we have too many keys in units drop the not common ones
                if (units.size > values.size)
                    return glue(units.filterKeys((units.keys intersect values.keys)::contains), values) // retry
                // if we don't have any data at this point we can safely return an empty map
                if (units.isEmpty())
                    return emptyMap()
                // get just the time keys array
                val time = values.getValue("time")
                    .requireNoNulls()
                    // get Time objects
                    .map { Time(it) }
                // filter time away! We don't need it in the map values
                return values.filterKeys { it != "time" }
                    .mapValues { (k, v) ->
                        UnitTimeStepValues(
                            units[k]!!, // no need to check if key exists, we already fixed shit in the beginning!
                            time.zip(v).toMap()
                        ) // create a map with the time as keys and the values as... values :)
                    }
            }
        }

        operator fun component1() = unit
        operator fun component2() = values
    }

    /**
     * Resources that may have daily data entries.
     */
    interface Daily : Response {
        val dailyUnits: Map<String, Units>
        val dailyValues: Map<String, Array<Double?>>

        @ExperimentalGluedUnitTimeStepValues
        val daily
            get() = UnitTimeStepValues
                .glue(dailyUnits, dailyValues)
    }

    /**
     * Resources that may have hourly data entries.
     */
    interface Hourly : Response {
        val hourlyUnits: Map<String, Units>
        val hourlyValues: Map<String, Array<Double?>>

        @ExperimentalGluedUnitTimeStepValues
        val hourly
            get() = UnitTimeStepValues
                .glue(hourlyUnits, hourlyValues)
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
