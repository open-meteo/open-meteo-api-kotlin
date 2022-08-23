package com.openmeteo.api.geocoding.response

import com.openmeteo.api.geocoding.response.GeocodingGet
import kotlinx.serialization.Serializable

@Serializable
class GeocodingSearch(
    val results: Array<GeocodingGet>,
    val generationtime_ms: Float,
)
