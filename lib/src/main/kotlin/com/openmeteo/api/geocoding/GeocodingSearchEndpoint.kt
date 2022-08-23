package com.openmeteo.api.geocoding

import com.openmeteo.api.common.net.Endpoint
import com.openmeteo.api.common.net.ContentFormat
import com.openmeteo.api.geocoding.response.GeocodingSearch
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL

class GeocodingSearchEndpoint(
    context: URL = URL("https://geocoding-api.open-meteo.com/v1/search/")
) : Endpoint(context) {
    @ExperimentalSerializationApi
    operator fun invoke(
        name: String,
        count: Int? = null,
        format: ContentFormat? = null,
        language: String? = null,
        vararg params: Pair<String, Any>,
    ) = query<GeocodingSearch>(
        "name" to name,
        "count" to count,
        "format" to format,
        "language" to language,
        *params,
    )
}
