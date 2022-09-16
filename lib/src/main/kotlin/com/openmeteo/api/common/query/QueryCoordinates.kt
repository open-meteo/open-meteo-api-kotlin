package com.openmeteo.api.common.query

interface QueryCoordinates : Query {
    val latitude: Float
    val longitude: Float
}
