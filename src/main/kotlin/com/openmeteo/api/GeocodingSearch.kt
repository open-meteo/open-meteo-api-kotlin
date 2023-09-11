package com.openmeteo.api

import com.openmeteo.api.common.http.ContentFormat
import com.openmeteo.api.common.http.Endpoint
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object GeocodingSearch : Endpoint(
    URL("https://geocoding-api.open-meteo.com/v1/search")
) {

    /**
     * Fetch a list of locations (by default count is set to 10).
     * @param query The [Query] to use.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     */
    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    /**
     * Fetch a list of locations (by default count is set to 10).
     * @param name A full or partial sequence of characters to use for the search of locations.
     * @param apikey The optional API key.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     */
    inline operator fun invoke(
        name: String,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = Query(name, apikey = apikey).let {
        it.query()
        this(it, context)
    }

    /**
     * Fetch only the first location matching the name query string.
     * @param name A full or partial sequence of characters to use for the search of the location.
     * @param apikey The optional API key.
     * @param context The API endpoint to use. Useful for self-hosted server instances.
     * @param query The query modifier.
     * @return The "one and only" location.
     */
    inline fun first(
        name: String,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = Query(name, apikey = apikey, count = 1).let {
        it.query()
        this(it, context)
    }.map { it.results[0] }

    @Serializable
    open class Query(
        var name: String,
        var count: Int? = null,
        var language: String? = null,
        override var apikey: String? = null,
        override var format: ContentFormat? = ContentFormat.ProtoBuf,
    ) : Q.ContentFormat, Q.CommercialLicense {
        init {
            require(name.length > 1)
        }
    }

    @Serializable
    open class Response(
        val results: Array<GeocodingGet.Response>,
        override val generationtimeMs: Float,
    ) : R.GenerationTimed {
        operator fun component1(): Array<GeocodingGet.Response> = results
    }

}
