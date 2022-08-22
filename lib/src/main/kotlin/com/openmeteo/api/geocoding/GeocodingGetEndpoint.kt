package com.openmeteo.api.geocoding

import com.openmeteo.api.common.Endpoint
import com.openmeteo.api.geocoding.serials.GeocodingGet
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL

class GeocodingGetEndpoint(
    context: URL = URL("https://geocoding-api.open-meteo.com/v1/get/")
) : Endpoint(context) {
    @ExperimentalSerializationApi
    operator fun invoke(
        id: Int,
        vararg params: Pair<String, Any>,
    ) = query<GeocodingGet>("id" to id, *params)
}
