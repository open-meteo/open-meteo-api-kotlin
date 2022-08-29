package com.openmeteo.apix.common.coordinates

import com.openmeteo.apix.common.http.Endpoint
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
interface CoordinatesEndpoint : Endpoint {
    val latitude: Float
    val longitude: Float
}
