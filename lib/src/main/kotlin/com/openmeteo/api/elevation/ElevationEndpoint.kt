package com.openmeteo.api.elevation

import com.openmeteo.api.common.net.Endpoint
import com.openmeteo.api.elevation.serials.Elevation
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URL

class ElevationEndpoint(
    context: URL = URL("https://api.open-meteo.com/v1/elevation/")
) : Endpoint(context) {

    @ExperimentalSerializationApi
    operator fun invoke(
        coordinates: Map<Float, Float>,
        vararg params: Pair<String, Any>,
    ) = if (coordinates.isEmpty())
        throw Error("Please provide at least one coordinate")
    else if (coordinates.size > 100)
        throw Error("Please provide no more then 100 coordinates")
    else query<Elevation>(
        "latitude" to coordinates.keys.joinToString(","),
        "longitude" to coordinates.values.joinToString(","),
        *params,
    )

    @ExperimentalSerializationApi
    operator fun invoke(
        vararg coordinates: Pair<Float, Float>,
    ) = invoke(mapOf(*coordinates))

}
