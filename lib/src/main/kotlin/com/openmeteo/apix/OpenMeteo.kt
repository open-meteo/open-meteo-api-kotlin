package com.openmeteo.apix

import com.openmeteo.apix.airquality.AirQuality
import com.openmeteo.apix.airquality.Domains
import com.openmeteo.apix.airquality.Hourly
import com.openmeteo.apix.common.http.Endpoint
import com.openmeteo.apix.common.query.QueryCoordinates
import com.openmeteo.apix.common.time.Date
import com.openmeteo.apix.common.time.TimeZone
import com.openmeteo.apix.common.units.PrecipitationUnit
import com.openmeteo.apix.common.units.TemperatureUnit
import com.openmeteo.apix.common.units.WindSpeedUnit
import com.openmeteo.apix.ecmwf.Ecmwf
import com.openmeteo.apix.elevation.Elevation
import com.openmeteo.apix.geocoding.GeocodingGet
import com.openmeteo.apix.geocoding.GeocodingSearch

class OpenMeteo(
    override val latitude: Float = 0f,
    override val longitude: Float = 0f,
    val airQuality: Endpoint = Endpoint(AirQuality.context),
    val ecmwf: Endpoint = Endpoint(Ecmwf.context),
    val elevation: Endpoint = Endpoint(Elevation.context),
    val geocodingGet: Endpoint = Endpoint(GeocodingGet.context),
    val geocodingSearch: Endpoint = Endpoint(GeocodingSearch.context),
) : QueryCoordinates {

    constructor(coordinates: Pair<Float, Float>) : this(
        coordinates.first,
        coordinates.second,
    )

    constructor(name: String) : this(
        Endpoint(GeocodingSearch.context)
            .query<GeocodingSearch.Response>(GeocodingSearch.Query(name, 1))
            .getOrNull()?.results?.get(0)
            ?.run { latitude to longitude }
            ?: Pair(0f, 0f)
    )

    operator fun invoke(query: AirQuality.Query) =
        airQuality.query<AirQuality.Response>(query)

    operator fun invoke(query: Ecmwf.Query) =
        ecmwf.query<Ecmwf.Response>(query)

    operator fun invoke(query: Elevation.Query) =
        elevation.query<Elevation.Response>(query)

    operator fun invoke(query: GeocodingGet.Query) =
        geocodingGet.query<GeocodingGet.Response>(query)

    operator fun invoke(query: GeocodingSearch.Query) =
        geocodingSearch.query<GeocodingSearch.Response>(query)

    fun airQuality(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<Hourly>? = null,
        domains: Domains? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
    ) = invoke(AirQuality.Query(latitude, longitude, hourly, domains, timeZone,
        startDate, endDate, pastDays))

    fun ecmwf(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<com.openmeteo.apix.ecmwf.Hourly>? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
    ) = invoke(Ecmwf.Query(latitude, longitude, hourly, temperatureUnit,
        windSpeedUnit, precipitationUnit, timeZone, startDate, endDate, pastDays))

    fun elevation(
        vararg coordinates: Pair<Float, Float>,
    ) = invoke(Elevation.Query(*coordinates))

    fun geocoding(
        id: Int,
    ) = invoke(GeocodingGet.Query(id))

    fun geocoding(
        name: String,
        count: Int? = null,
        language: String? = null,
    ) = invoke(GeocodingSearch.Query(name, count, language))

}
