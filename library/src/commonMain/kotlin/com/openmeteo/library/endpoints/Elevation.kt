package com.openmeteo.library.endpoints

import com.openmeteo.library.Decoding
import com.openmeteo.library.Query
import com.openmeteo.library.serializers.ListAsString
import kotlinx.serialization.Serializable

@Serializable
public data class Elevation(
    val latitude: ListAsString<Float> = emptyList(),
    val longitude: ListAsString<Float> = emptyList(),
) : Query<Elevation, Elevation.Response>
by Decoding.json("https://api.open-meteo.com/v1/elevation") {

    @Serializable
    public data class Response(
        val elevation: List<Float>
    )

}