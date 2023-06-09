package com.openmeteo.api

import com.openmeteo.api.common.Coordinate
import com.openmeteo.api.common.http.Endpoint
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object Elevation : Endpoint(
    URL("https://api.open-meteo.com/v1/elevation")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    @Serializable
    open class Query private constructor(
        val latitude: String,
        val longitude: String,
        override val apikey: String? = null,
    ) : Q.CommercialLicense {
        constructor(latitude: Float, longitude: Float) : this(
            latitude.toString(),
            longitude.toString()
        )
        constructor(latitudes: List<Float>, longitudes: List<Float>) : this(
            latitudes.joinToString(","),
            longitudes.joinToString(","),
        )
        constructor(vararg coordinates: Coordinate) : this(
            coordinates.map { it.latitude },
            coordinates.map { it.longitude },
        )
        constructor(vararg coordinates: Pair<Float, Float>) : this(
            coordinates.map { it.first },
            coordinates.map { it.second },
        )

    }

    @Serializable
    open class Response(
        val elevation: FloatArray,
    ) : R

}
