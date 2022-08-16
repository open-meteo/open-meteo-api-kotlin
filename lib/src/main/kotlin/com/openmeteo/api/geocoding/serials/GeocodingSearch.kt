package com.openmeteo.api.geocoding.serials

import kotlinx.serialization.Serializable

@Serializable
class GeocodingSearch(
    val results: Array<GeocodingGet>,
    val generationtime_ms: Float,
)