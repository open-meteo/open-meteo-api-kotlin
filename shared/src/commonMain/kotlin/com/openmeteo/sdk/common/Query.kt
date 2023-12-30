package com.openmeteo.sdk.common

//import com.openmeteo.sdk.common.time.Date
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToStringMap

interface Query {

    companion object {

        /**
         * Stringify and URL encode a value.
         */
        fun encode(value: Any, encoding: String = "utf-8"): String? =
            // URLEncoder.encode(value.toString(), encoding)
            value.toString()

        /**
         * Retrieve a flat [Map] of key-value pairs.
         * Entries with null values are filtered out.
         */
        @OptIn(ExperimentalSerializationApi::class)
        inline fun <reified T : Query> toMap(query: T) =
            Properties.encodeToStringMap(query)
                .filter { (k) -> k != "type" }
                .let {
                    // if resource has timestamps
                    if (query is TimeFormat)
                        // always force unix timestamps
                        it.plus("timeformat" to "unixtime")
                    else it
                }

        /**
         * Like `.toMap().toList()`
         */
        inline fun <reified T : Query> toList(query: T) =
            toMap(query).toList()

        /**
         * Encode the query as `?key0=value0&key1=value1&...keyN=valueN`.
         *
         * To URL encode values use the [encode] method.
         * Note that key names (usually) don't need to be URL encoded.
         */
        inline fun <reified T : Query> asString(query: T) =
            toList(query)
                .joinToString("&", "?") { (k, v) -> "$k=$v" }

        /**
         * Encode the query as `?key0=value0&key1=value1&...keyN=valueN`.
         *
         * TODO: Values should be URL encoded.
         */
        inline fun <reified T : Query> toURL(query: T, context: String /*URL*/) =
            // URL(context, "${context.path}${asString(query)}")
            "$context?${asString(query)}"

    }

    /**
     * Query for resources that have a timezone field.
     */
    interface Timezone : Query {
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
    interface TimeFormat : Query

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
    interface DateRange : Query {

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
    interface PastDays : Query {

        /**
         * The number of days in the past to query.
         */
        val pastDays: Int?
    }

    /**
     * Query for resources that can be retrieved from future days.
     */
    interface ForecastDays : Query {

        /**
         * The number of days in the future to query.
         */
        val forecastDays: Int?
    }

    /**
     * Query for resources that may pick the content format.
     */
    interface ContentFormat : Query {
        /**
         * The requested content format.
         */
        val format: com.openmeteo.sdk.common.http.ContentFormat?
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
    interface CurrentWeather : Query {
        /**
         * Whether to include the current weather in the response or not.
         */
        val currentWeather: Boolean?
    }

    /**
     * Query for resources that may include temperature values
     */
    interface TemperatureUnit : Query {
        /**
         * The requested temperature unit
         */
        val temperatureUnit: com.openmeteo.sdk.common.units.TemperatureUnit?
    }

    /**
     * Query for resources that may include wind speed values
     */
    interface WindSpeedUnit : Query {
        /**
         * The requested wind speed unit
         */
        val windSpeedUnit: com.openmeteo.sdk.common.units.WindSpeedUnit?
    }

    /**
     * Query for resources that may include wind speed values
     */
    interface PrecipitationUnit : Query {
        /**
         * The requested wind speed unit
         */
        val precipitationUnit: com.openmeteo.sdk.common.units.PrecipitationUnit?
    }

    /**
     * Query for resources that may include lengths
     */
    interface LengthUnit : Query {
        /**
         * The requested length unit
         */
        val lengthUnit: com.openmeteo.sdk.common.units.LengthUnit?
    }

    /**
     * Query for resources that can use different data models.
     */
    interface Models : Query {
        /**
         * The requested model
         */
        val models: String?
    }

    /**
     * Query for resources that can have different cell selection.
     */
    interface CellSelection : Query {
        /**
         * The requested cell selection
         */
        val cellSelection: com.openmeteo.sdk.common.CellSelection?
    }

    /**
     * Query that may be retrieved with a commercial license (api key)
     */
    interface CommercialLicense : Query {
        /**
         * The api key (commercial usage)
         * Commercial usage appends `customer-` as prefix on all domains:
         * - `https://customer-archive-api.open-meteo.com/v1/archive`
         * - `https://customer-marine-api.open-meteo.com/v1/marine`
         */
        val apikey: String?
    }

}
