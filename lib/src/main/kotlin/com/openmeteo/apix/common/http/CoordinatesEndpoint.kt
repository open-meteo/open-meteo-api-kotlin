package com.openmeteo.apix.common.http

import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
interface CoordinatesEndpoint : Endpoint {
    val latitude: Float
    val longitude: Float
}
