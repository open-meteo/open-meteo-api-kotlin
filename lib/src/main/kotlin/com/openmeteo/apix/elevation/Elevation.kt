package com.openmeteo.apix.elevation

import com.openmeteo.apix.common.http.Endpoint
import java.net.URL

class Elevation(
    val latitude: Iterable<Float>,
    val longitude: Iterable<Float>,
    context: URL = URL("https://api.open-meteo.com/v1/elevation"),
) : Endpoint(context) {
    constructor(vararg coordinates: Pair<Float, Float>) : this(
        coordinates.map { it.first },
        coordinates.map { it.second },
    )
}
