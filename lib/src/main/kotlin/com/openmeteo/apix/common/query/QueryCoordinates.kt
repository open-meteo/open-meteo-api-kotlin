package com.openmeteo.apix.common.query

interface QueryCoordinates : Query {
    val latitude: Float
    val longitude: Float
}
