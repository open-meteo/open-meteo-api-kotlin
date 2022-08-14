package com.openmeteo.api.elevation

import com.openmeteo.api.common.Endpoint
import java.net.URL

class ElevationEndpoint(
    override val context: URL = URL("https://api.open-meteo.com/v1/elevation/")
) : Endpoint {

    operator fun invoke(
        coordinates: Map<Float, Float>,
    ) = if (coordinates.isEmpty())
        throw Error("Please provide at least one coordinate")
    else if (coordinates.size > 100)
        throw Error("Please provide no more then 100 coordinates")
    else query(
        "latitude" to coordinates.keys,
        "longitude" to coordinates.values,
    )

    operator fun invoke(
        vararg coordinates: Pair<Float, Float>,
    ) = invoke(mapOf(*coordinates))

}