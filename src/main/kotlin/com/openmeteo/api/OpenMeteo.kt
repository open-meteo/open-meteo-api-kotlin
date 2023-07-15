package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import java.net.URL

/**
 * A global class to ease latitude/longitude and geocoding api calls.
 *
 * Usage example:
 * ```
 * @OptIn(Response.GlueUnitTimeStepValues::class)
 * fun main() {
 *     val om = OpenMeteo("Trieste", "it").getOrThrow()
 *     val forecast = om.forecast {
 *         daily = Forecast.Daily {
 *             listOf(temperature2mMin, temperature2mMax)
 *         }
 *         temperatureUnit = TemperatureUnit.Fahrenheit
 *         timezone = Timezone.auto
 *     }.getOrThrow()
 *     Forecast.Daily.run {
 *         forecast.daily.getValue(temperature2mMax).run {
 *             println("# $temperature2mMax ($unit)")
 *             values.forEach{ (t, v) -> println("> $t | $v") }
 *         }
 *     }
 * }
 * ```
 */
open class OpenMeteo(
    var latitude: Float,
    var longitude: Float,
    var apikey: String? = null,
) {

    @Deprecated(
        "Hardcoded Cities are deprecated: use the geocoding API instead!",
        ReplaceWith("OpenMeteo(..., language, apikey)"),
        DeprecationLevel.WARNING
    )
    constructor(city: City, apikey: String? = null)
        : this(city.latitude, city.longitude, apikey)

    companion object {

        @Deprecated(
            "Language setting is pointless with the id: we only care about coordinates (floats)!",
            ReplaceWith("OpenMeteo(id, apikey)"),
            DeprecationLevel.WARNING
        )
        operator fun invoke(id: Int, language: String? = null, apikey: String? = null) =
            GeocodingGet(id) {
                this.language = language
                this.apikey = apikey
            }.map { OpenMeteo(it.latitude, it.longitude, apikey) }

        operator fun invoke(id: Int, apikey: String? = null) =
            GeocodingGet(id) { this.apikey = apikey }.map { OpenMeteo(it.latitude, it.longitude, apikey) }

        /**
         * Search a location with the Geocoding API.
         * Fetches one element and uses its coordinates by default.
         * Note that language is only used in the query and it is **not** stored
         * (e.g. search "Roma" instead of "Rome", with "it" as language).
         */
        operator fun invoke(location: String, language: String? = null, apikey: String? = null) =
            GeocodingSearch(location) {
                this.language = language
                this.count = 1
                this.apikey = apikey
            }.mapCatching { (results) ->
                if (results.isEmpty()) throw Error("No results!")
                OpenMeteo(results[0].latitude, results[0].longitude, apikey)
            }

    }

    inline fun airQuality(
        context: URL = AirQuality.context,
        query: AirQuality.Query.() -> Unit,
    ) = AirQuality(latitude, longitude, apikey, context, query)

    inline fun climateChange(
        models: String,
        startDate: Date,
        endDate: Date,
        context: URL = ClimateChange.context,
        query: ClimateChange.Query.() -> Unit,
    ) = ClimateChange(latitude, longitude, models, startDate, endDate, apikey, context, query)

    inline fun elevation(
        context: URL = Elevation.context,
        query: Elevation.Query.() -> Unit,
    ) = Elevation(latitude, longitude, apikey, context, query)

    inline fun ensemble(
        context: URL = Ensemble.context,
        query: Ensemble.Query.() -> Unit,
    ) = Ensemble(latitude, longitude, apikey, context, query)

    inline fun flood(
        context: URL = Flood.context,
        query: Flood.Query.() -> Unit,
    ) = Flood(latitude, longitude, apikey, context, query)

    inline fun forecast(
        context: URL = Forecast.context,
        query: Forecast.Query.() -> Unit,
    ) = Forecast(latitude, longitude, apikey, context, query)

    inline fun historical(
        startDate: Date,
        endDate: Date,
        context: URL = Historical.context,
        query: Historical.Query.() -> Unit,
    ) = Historical(latitude, longitude, startDate, endDate, apikey, context, query)

    inline fun marine(
        context: URL = Marine.context,
        query: Marine.Query.() -> Unit,
    ) = Marine(latitude, longitude, apikey, context, query)

}
