package com.openmeteo.apix.common.response

interface ResponseCoordinates : Response {
    val latitude: Float
    val longitude: Float
}
