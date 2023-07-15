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

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    inline operator fun invoke(
        name: String,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit,
    ) = Query(name, apikey = apikey).let {
        it.query()
        this(it, context)
    }

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
