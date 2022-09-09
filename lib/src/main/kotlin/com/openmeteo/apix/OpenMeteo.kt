package com.openmeteo.apix

import com.openmeteo.apix.common.query.QueryCoordinates
import com.openmeteo.apix.elevation.Elevation
import com.openmeteo.apix.geocoding.GeocodingGet
import com.openmeteo.apix.geocoding.GeocodingSearch

class OpenMeteo(
    override val latitude: Float = 0f,
    override val longitude: Float = 0f,
) : QueryCoordinates {

    constructor(coordinates: Pair<Float, Float>) : this(
        coordinates.first,
        coordinates.second,
    )

    constructor(name: String) : this(
        GeocodingSearch(name)().getOrNull()
            ?.results?.get(0)?.run { latitude to longitude }
            ?: Pair(0f, 0f)
    )

    fun geocoding(
        id: Int,
    ) = GeocodingGet(id)()

    fun geocoding(
        name: String,
        count: Int,
        language: String,
    ) = GeocodingSearch(name, count, language)()

    fun elevation(
        latitude: Collection<Float> = listOf(this.latitude),
        longitude: Collection<Float> = listOf(this.longitude),
    ) = Elevation(latitude, longitude)()

    fun elevation(
        vararg coordinates: Pair<Float, Float>,
    ) = Elevation(*coordinates)()

}
