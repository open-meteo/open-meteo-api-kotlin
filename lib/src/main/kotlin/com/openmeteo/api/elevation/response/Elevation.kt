package com.openmeteo.api.elevation.response

import kotlinx.serialization.Serializable

@Serializable
class Elevation(
    val elevation: FloatArray,
)
