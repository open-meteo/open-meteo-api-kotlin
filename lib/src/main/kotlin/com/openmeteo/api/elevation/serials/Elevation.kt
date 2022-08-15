package com.openmeteo.api.elevation.serials

import kotlinx.serialization.Serializable

@Serializable
class Elevation(
    val elevation: FloatArray,
)