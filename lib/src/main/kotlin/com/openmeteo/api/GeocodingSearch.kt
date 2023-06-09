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

    operator fun invoke(query: Query) = query<Response, Query>(query)

    @Serializable
    open class Query(
        val name: String,
        val count: Int? = null,
        val language: String? = null,
        override val apikey: String? = null,
        override val format: ContentFormat? = ContentFormat.ProtoBuf,
    ) : Q.ContentFormat, Q.CommercialLicense {
        init {
            require(name.length > 1)
        }
    }

    @Serializable
    open class Response(
        val results: Array<GeocodingGet.Response>,
        override val generationtimeMs: Float,
    ) : R.GenerationTimed

}
