package com.openmeteo.api.common.response

interface ResponseCoordinates : Response {
    val latitude: Float
    val longitude: Float
}
