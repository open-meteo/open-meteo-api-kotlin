package com.openmeteo.sdk.common

//import com.openmeteo.sdk.common.time.Date
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToMap

@Serializable
open class Query {

    /**
     * Query for resources that have a timezone field.
     */
    interface Timezone {
        /**
         * The timezone to optionally use.
         */
        //val timezone: com.openmeteo.sdk.common.time.Timezone?
    }

    /**
     * Query for resources that have (formatted) time fields.
     *
     * Used internally to hardcode Unix timestamps.
     */
    interface TimeFormat

    /**
     * Query for resources that have daily fields.
     */
    interface Daily : TimeFormat {
        /**
         * The daily fields queried.
         */
        val daily: String?
    }

    /**
     * Query for resources that have hourly fields.
     */
    interface Hourly : TimeFormat {
        /**
         * The hourly fields queried.
         */
        val hourly: String?
    }

    /**
     * Query for resources that can be retrieved in a specific date range.
     */
    interface DateRange {

        /**
         * The start date of the range.
         */
        //val startDate: Date?

        /**
         * The end date of the range.
         */
        //val endDate: Date?
    }

    /**
     * Query for resources that can be retrieved from past days.
     */
    interface PastDays {

        /**
         * The number of days in the past to query.
         */
        val pastDays: Int?
    }

    /**
     * Query for resources that can be retrieved from future days.
     */
    interface ForecastDays {

        /**
         * The number of days in the future to query.
         */
        val forecastDays: Int?
    }

    /**
     * Query for resources that may pick the content format.
     */
    interface ContentFormat {
        /**
         * The requested content format.
         */
        val format: com.openmeteo.sdk.common.ContentFormat?
    }

    /**
     * Query for location-based resources.
     */
    interface Coordinate : com.openmeteo.sdk.common.Coordinate, Query

    /**
     * Query for elevation-based resources.  Defaults to a 90 meters model.
     */
    interface Elevation : com.openmeteo.sdk.common.Elevation

    /**
     * Query for resources that may include the current weather in the response.
     */
    interface CurrentWeather {
        /**
         * Whether to include the current weather in the response or not.
         */
        val currentWeather: Boolean?
    }

    /**
     * Query for resources that may include temperature values
     */
    interface TemperatureUnit {
        /**
         * The requested temperature unit
         */
        val temperatureUnit: com.openmeteo.sdk.common.units.TemperatureUnit?
    }

    /**
     * Query for resources that may include wind speed values
     */
    interface WindSpeedUnit {
        /**
         * The requested wind speed unit
         */
        val windSpeedUnit: com.openmeteo.sdk.common.units.WindSpeedUnit?
    }

    /**
     * Query for resources that may include wind speed values
     */
    interface PrecipitationUnit {
        /**
         * The requested wind speed unit
         */
        val precipitationUnit: com.openmeteo.sdk.common.units.PrecipitationUnit?
    }

    /**
     * Query for resources that may include lengths
     */
    interface LengthUnit {
        /**
         * The requested length unit
         */
        val lengthUnit: com.openmeteo.sdk.common.units.LengthUnit?
    }

    /**
     * Query for resources that can use different data models.
     */
    interface Models {
        /**
         * The requested model
         */
        val models: String?
    }

    /**
     * Query for resources that can have different cell selection.
     */
    interface CellSelection {
        /**
         * The requested cell selection
         */
        val cellSelection: com.openmeteo.sdk.common.CellSelection?
    }

    /**
     * Query that may be retrieved with a commercial license (api key)
     */
    interface CommercialLicense {
        /**
         * The api key (commercial usage)
         * Commercial usage appends `customer-` as prefix on all domains:
         * - `https://customer-archive-api.open-meteo.com/v1/archive`
         * - `https://customer-marine-api.open-meteo.com/v1/marine`
         */
        val apikey: String?
    }

    companion object {

        /**
         * Encode a query as an HTTP query string, i.e. `?a=hello_world&b=3&c=true`
         * Please note that spaces and other special characters should be manually
         * encoded through the `transform` lambda (defaults to `{ (k, v) -> "$k=$v" }`).
         * Please note that pairs with `null` values are skipped.
         * @param value The [Query] to encode.
         * @param transform The transform function applied to key-value pairs.
         * @return Empty string if null query, otherwise encoded string.
         */
        @OptIn(ExperimentalSerializationApi::class)
        inline fun <reified T : Query> encodeToString(
            value: T?,
            noinline transform: ((Pair<String, Any>) -> String) = { (k, v) -> "$k=$v" }
        ) =
            if (value == null) ""
            else Properties.encodeToMap(value).toList()
                .joinToString("&", "?", transform = transform)

    }

}
