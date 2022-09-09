package com.openmeteo.apix.geocoding

import com.openmeteo.apix.common.http.ContentFormat
import com.openmeteo.apix.common.http.Endpoint
import com.openmeteo.apix.common.query.Query
import com.openmeteo.apix.common.query.QueryContentFormat
import com.openmeteo.apix.common.response.ResponseGenerationTimed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

open class GeocodingSearch(
    val name: String,
    @SerialName("count")
    val count: Int? = null,
    val language: String? = null,
    context: URL = Companion.context,
) : Endpoint(context),
    QueryContentFormat {

    override val format: ContentFormat = ContentFormat.ProtoBuf

    companion object {
        val context = URL("https://geocoding-api.open-meteo.com/v1/search")
    }

    init {
        count?.let { require(it in 1..100) }
    }

    @Serializable
    open class Response(
        val results: Array<GeocodingGet.Response>,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseGenerationTimed

    operator fun invoke(query: Query? = null) = query<Response>(query)

}
