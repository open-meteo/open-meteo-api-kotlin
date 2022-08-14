package com.openmeteo.api.geocoding

import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.common.params.*
import java.net.URL

class GeocodingEndpoint(
    override val context: URL = URL("https://geocoding-api.open-meteo.com/v1/search/")
) : Endpoint {
    operator fun invoke(
        name: String,
        count: Int? = null,
        format: ContentFormat? = null,
        language: String? = null,
    ) = query(
        "name" to name,
        "count" to count,
        "format" to format,
        "language" to language,
    )
    /* Temporarily disabled
    operator fun invoke(
        id: Int,
    ) = kotlin.runCatching {
        get(URL("https://geocoding-api.open-meteo.com/v1/get?id=$id"))
    } */
}