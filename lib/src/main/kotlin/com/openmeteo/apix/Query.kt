package com.openmeteo.apix

import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeFormat.UnixTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToStringMap
import java.net.URLEncoder.encode

sealed interface Query {

    /**
     * Retrieve a flat [Map] of key-value pairs.
     * Entries with null values are filtered out.
     */
    @OptIn(ExperimentalSerializationApi::class)
    fun toMap() =
        Properties.encodeToStringMap(this)
            .filter { (k) -> k != "type" }

    /**
     * Like `.toMap().toList()`
     */
    fun toList() =
        toMap().toList()

    /**
     * Encode the query as `?key0=value0&key1=value1&...keyN=valueN`.
     *
     * Values are URL encoded.
     */
    fun asString() =
        toList().joinToString("&", "?") {
                (k, v) -> "$k=${encode(v, "utf-8")}"
        }

    /**
     * A parent type for [Daily] and [Hourly] options.
     */
    interface Options

    /**
     * Query for resources that have a timezone field.
     */
    sealed interface TimeZone : Query {
        /**
         * The timezone to optionally use.
         */
        @SerialName("timezone")
        val timeZone: com.openmeteo.api.common.time.TimeZone?
    }

    /**
     * Query for resources that have (formatted) time fields.
     */
    sealed interface TimeFormat : Query {
        /**
         * The time format that should be used in the response.
         *
         * Hardcoded to unix time, since it is easier to parse.
         */
        @SerialName("timeformat")
        val timeFormat get() = UnixTime
    }

    /**
     * Query for resources that have daily fields.
     */
    sealed interface Daily : TimeFormat {
        /**
         * The daily fields queried.
         */
        val daily: Iterable<Options>?

        /**
         * A generic type to identify [Daily] options.
         */
        interface Options : Query.Options
    }

    /**
     * Query for resources that have hourly fields.
     */
    sealed interface Hourly : TimeFormat {
        /**
         * The hourly fields queried.
         */
        val hourly: Iterable<Options>?

        /**
         * A generic type to identify [Hourly] options.
         */
        interface Options : Query.Options
    }

    /**
     * Query for resources that can be retrieved in a specific date range.
     */
    sealed interface DateRange : Query {

        /**
         * The start date of the range.
         */
        @SerialName("start_date")
        val startDate: Date?

        /**
         * The end date of the range.
         */
        @SerialName("end_date")
        val endDate: Date?
    }

    /**
     * Query for resources that can be retrieved from past days.
     */
    sealed interface PastDays : Query {

        /**
         * The number of days in the past to query.
         */
        @SerialName("past_days")
        val pastDays: Int?
    }

    /**
     * Query for resources that can be retrieved from future days.
     */
    sealed interface ForecastDays : Query {

        /**
         * The number of days in the future to query.
         */
        @SerialName("forecast_days")
        val forecastDays: Int?
    }

    /**
     * Query for resources that may pick the content format.
     */
    sealed interface ContentFormat : Query {
        /**
         * The requested content format.
         */
        val format: com.openmeteo.api.common.http.ContentFormat? get() = null
    }

    /**
     * Query for location-based resources.
     */
    sealed interface Coordinate : com.openmeteo.apix.Coordinate, Query

    /**
     * Query for resources that may include the current weather in the response.
     */
    sealed interface CurrentWeather : Query {
        /**
         * Whether to include the current weather in the response or not.
         */
        @SerialName("current_weather")
        val currentWeather: Boolean?
    }
}
