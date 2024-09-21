package com.openmeteo.library.endpoints

import com.openmeteo.library.Decoding
import com.openmeteo.library.Expect
import com.openmeteo.library.Query
import com.openmeteo.library.serializers.ListAsString
import kotlinx.serialization.Serializable

@Serializable
public data class Elevation(
    override val latitude: ListAsString<Float> = emptyList(),
    override val longitude: ListAsString<Float> = emptyList(),
) : Query("https://api.open-meteo.com/v1/elevation"),
    Query.Coordinates.Multiple,
    Expect<Elevation.Response> by Decoding.json() {

    @Serializable
    public data class Response(
        val elevation: List<Float>
    )

}