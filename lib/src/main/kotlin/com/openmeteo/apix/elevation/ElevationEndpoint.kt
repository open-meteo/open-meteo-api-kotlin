package com.openmeteo.apix.elevation

import com.openmeteo.apix.common.http.Endpoint
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL

class ElevationEndpoint(
    override val context: URL
) : Endpoint {
    override val serializer = ElevationResponse.serializer()

    @ExperimentalSerializationApi
    operator fun invoke(
        coordinates: Map<Float, Float>,
        vararg params: Pair<String, Any>,
    ) = if (coordinates.isEmpty())
        throw Error("Please provide at least one coordinate")
    else if (coordinates.size > 100)
        throw Error("Please provide no more than 100 coordinates")
    else query(
        "latitude" to coordinates.keys.joinToString(","),
        "longitude" to coordinates.values.joinToString(","),
        *params,
    )

    @ExperimentalSerializationApi
    operator fun invoke(
        vararg coordinates: Pair<Float, Float>,
    ) = invoke(mapOf(*coordinates))
}
