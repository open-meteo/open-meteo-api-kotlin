package com.openmeteo.api.common.query

import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeFormat.UnixTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToStringMap
import java.net.URL
import java.net.URLEncoder.encode

interface Query {

    companion object {

        /**
         * Retrieve a flat [Map] of key-value pairs.
         * Entries with null values are filtered out.
         */
        @OptIn(ExperimentalSerializationApi::class)
        inline fun <reified T : Query> toMap(query: T) =
            Properties.encodeToStringMap(query)
                .filter { (k) -> k != "type" }

        /**
         * Like `.toMap().toList()`
         */
        inline fun <reified T : Query> toList(query: T) =
            toMap(query).toList()

        /**
         * Encode the query as `?key0=value0&key1=value1&...keyN=valueN`.
         *
         * Values are URL encoded.
         */
        inline fun <reified T : Query> asString(query: T) =
            toList(query).joinToString("&", "?") { (k, v) ->
                "$k=${encode(v, "utf-8")}"
            }

        /**
         * Encode the query as `?key0=value0&key1=value1&...keyN=valueN`.
         *
         * Values are URL encoded.
         */
        inline fun <reified T : Query> toURL(query: T, context: URL) =
            URL(context, "${context.path}${asString(query)}")

    }

    /**
     * Query for resources that have a timezone field.
     */
    interface Timezone : Query {
        /**
         * The timezone to optionally use.
         */
        val timezone: com.openmeteo.api.common.time.Timezone?
    }

    /**
     * Query for resources that have (formatted) time fields.
     */
    interface TimeFormat : Query {
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
    interface PastDays : Query {

        /**
         * The number of days in the past to query.
         */
        @SerialName("past_days")
        val pastDays: Int?
    }

    /**
     * Query for resources that can be retrieved from future days.
     */
    interface ForecastDays : Query {

        /**
         * The number of days in the future to query.
         */
        @SerialName("forecast_days")
        val forecastDays: Int?
    }

    /**
     * Query for resources that may pick the content format.
     */
    interface ContentFormat : Query {
        /**
         * The requested content format.
         */
        val format: com.openmeteo.api.common.http.ContentFormat? get() = null
    }

    /**
     * Query for location-based resources.
     */
    interface Coordinate : com.openmeteo.api.common.Coordinate, Query

    /**
     * Query for resources that may include the current weather in the response.
     */
    interface CurrentWeather : Query {
        /**
         * Whether to include the current weather in the response or not.
         */
        @SerialName("current_weather")
        val currentWeather: Boolean?
    }
}
