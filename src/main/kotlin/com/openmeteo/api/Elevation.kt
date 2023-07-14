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
        var latitude: String,
        var longitude: String,
        override var apikey: String? = null,
    ) : Q.CommercialLicense {

        constructor(latitude: Float, longitude: Float, apikey: String? = null)
            : this(latitude.toString(), longitude.toString(), apikey)

        constructor(latitudes: List<Float>, longitudes: List<Float>, apikey: String? = null)
            : this(
                latitudes.joinToString(","),
                longitudes.joinToString(","),
                apikey,
            )

        constructor(vararg coordinates: Coordinate, apikey: String? = null)
            : this(
                coordinates.map { it.latitude },
                coordinates.map { it.longitude },
                apikey,
            )

        constructor(vararg coordinates: Pair<Float, Float>, apikey: String? = null)
            : this(
                coordinates.map { it.first },
                coordinates.map { it.second },
                apikey,
            )

    }

    @Serializable
    open class Response(
        val elevation: FloatArray,
    ) : R

}
