package com.openmeteo.api.geocoding

import com.openmeteo.api.common.http.ContentFormat
import com.openmeteo.api.common.query.QueryContentFormat
import com.openmeteo.api.common.response.ResponseGenerationTimed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

object GeocodingSearch {

    val context = URL("https://geocoding-api.open-meteo.com/v1/search")

    open class Query internal constructor(
        val name: String,
        val count: Int? = null,
        override val format: ContentFormat? = ContentFormat.ProtoBuf,
        val language: String? = null,
    ) : QueryContentFormat {

        constructor(
            name: String,
            count: Int? = null,
            language: String? = null,
        ) : this(
            name,
            count,
            ContentFormat.ProtoBuf,
            language,
        )

        init {
            require(count in 1..100)
        }

    }

    @Serializable
    open class Response(
        val results: Array<GeocodingGet.Response>,
        @SerialName("generationtime_ms")
        override val generationTimeMs: Float,
    ) : ResponseGenerationTimed

}
