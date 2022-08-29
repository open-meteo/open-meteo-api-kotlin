package com.openmeteo.apix.elevation

import kotlinx.serialization.Serializable

@Serializable
class ElevationResponse(
    val elevation: FloatArray,
)
