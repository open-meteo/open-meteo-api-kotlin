package com.openmeteo.apix.elevation

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.net.URL

object Elevation {

    val context = URL("https://api.open-meteo.com/v1/elevation")

    open class Query(
        val latitude: Collection<Float>,
        val longitude: Collection<Float>,
    ) : com.openmeteo.apix.common.query.Query {

        constructor(coordinates: Pair<Collection<Float>, Collection<Float>>) : this(
            coordinates.first,
            coordinates.second,
        )

        constructor(vararg coordinates: Pair<Float, Float>) : this(
            coordinates.unzip(),
        )

        init {
            require(latitude.size in 1..100)
            require(longitude.size == latitude.size)
            // require(longitude.size in 1..100)` // if (x in 0..100 && y == x) then (y in 0..100)
        }

        @Transient
        val coordinates
            get() =
                latitude.zip(longitude)

    }

    @Serializable
    open class Response(
        val elevation: FloatArray,
    )

}
