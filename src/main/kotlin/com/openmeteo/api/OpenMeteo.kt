package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import java.net.URL

/**
 * A global class to ease latitude/longitude and geocoding api calls.
 *
 * Usage example:
 * ```
 * import com.openmeteo.api.common.Response
 * import com.openmeteo.api.common.time.Timezone
 * import com.openmeteo.api.common.units.TemperatureUnit

 * @OptIn(Response.ExperimentalGluedUnitTimeStepValues::class)
 * fun main() {
 *     val om = OpenMeteo("Trieste", "it")
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
 *
 * @param latitude The stored location latitude.
 * @param longitude The stored location longitude.
 * @param apikey The optional API key.
 * @param contexts A list of URL endpoints contexts for all the implemented APIs.
 */
open class OpenMeteo(
    var latitude: Float,
    var longitude: Float,
    var apikey: String? = null,
    var contexts: Contexts = Contexts(),
) {

    /**
     * A list of URL endpoints contexts for all the implemented APIs.
     */
    class Contexts(
        var airQuality: URL = AirQuality.context,
        var climateChange: URL = ClimateChange.context,
        var elevation: URL = Elevation.context,
        var ensemble: URL = Ensemble.context,
        var flood: URL = Flood.context,
        var forecast: URL = Forecast.context,
        var geocodingGet: URL = GeocodingGet.context,
        var geocodingSearch: URL = GeocodingSearch.context,
        var historical: URL = Historical.context,
        var marine: URL = Marine.context,
    )

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

        /**
         * Retrieve an [OpenMeteo] instance of a specific location.
         * @param id The location unique id.
         * @param apikey The optional API key.
         */
        operator fun invoke(id: Int, apikey: String? = null, contexts: Contexts = Contexts()) =
            GeocodingGet(id, apikey, contexts.geocodingGet) { }
                .map { OpenMeteo(it.latitude, it.longitude, apikey, contexts) }

        /**
         * Search a location with the Geocoding API. Fetches one element and uses its coordinates by default.
         * For more flexibility use the full [GeocodingSearch] API.
         * @param location The name of the location to search for.
         * @param language The language to use for the search (e.g. search "Roma" instead of "Rome" with "it" as
         * language). Note that language is only used in the query and is **not stored**.
         * @param apikey The optional API key.
         */
        operator fun invoke(location: String, language: String? = null, apikey: String? = null, contexts: Contexts = Contexts()) =
            GeocodingSearch.first(location, apikey, contexts.geocodingSearch) {this.language = language }
                .getOrNull()?.let {
                    OpenMeteo(it.latitude, it.longitude, apikey)
                } ?: throw Error("No results!")

    }

    /**
     * Query the [AirQuality] API.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun airQuality(
        context: URL = contexts.airQuality,
        query: AirQuality.Query.() -> Unit,
    ) = AirQuality(latitude, longitude, apikey, context, query)

    /**
     * Query the [ClimateChange] API.
     * @param models The [ClimateChange.Models] to use.
     * @param startDate The time frame start date.
     * @param endDate The time frame end date.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun climateChange(
        models: String,
        startDate: Date,
        endDate: Date,
        context: URL = contexts.climateChange,
        query: ClimateChange.Query.() -> Unit,
    ) = ClimateChange(latitude, longitude, models, startDate, endDate, apikey, context, query)

    /**
     * Query the [Elevation] API.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun elevation(
        context: URL = contexts.elevation,
        query: Elevation.Query.() -> Unit,
    ) = Elevation(latitude, longitude, apikey, context, query)

    /**
     * Query the [Ensemble] API.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun ensemble(
        context: URL = contexts.ensemble,
        query: Ensemble.Query.() -> Unit,
    ) = Ensemble(latitude, longitude, apikey, context, query)

    /**
     * Query the [Flood] API.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun flood(
        context: URL = contexts.flood,
        query: Flood.Query.() -> Unit,
    ) = Flood(latitude, longitude, apikey, context, query)

    /**
     * Query the [Forecast] API.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun forecast(
        context: URL = contexts.forecast,
        query: Forecast.Query.() -> Unit,
    ) = Forecast(latitude, longitude, apikey, context, query)

    /**
     * Query the [Historical] API.
     * @param startDate The time frame start date.
     * @param endDate The time frame end date.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun historical(
        startDate: Date,
        endDate: Date,
        context: URL = contexts.historical,
        query: Historical.Query.() -> Unit,
    ) = Historical(latitude, longitude, startDate, endDate, apikey, context, query)

    /**
     * Query the [Marine] API.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline fun marine(
        context: URL = contexts.marine,
        query: Marine.Query.() -> Unit,
    ) = Marine(latitude, longitude, apikey, context, query)

}
