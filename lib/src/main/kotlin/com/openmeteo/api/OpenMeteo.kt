package com.openmeteo.api

import com.openmeteo.api.airquality.AirQuality
import com.openmeteo.api.airquality.AirQualityDomains
import com.openmeteo.api.airquality.AirQualityHourly
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.query.*
import com.openmeteo.api.common.response.ResponseCoordinates
import com.openmeteo.api.common.response.ResponseDaily
import com.openmeteo.api.common.response.ResponseHourly
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Unit
import com.openmeteo.api.common.units.WindSpeedUnit
import com.openmeteo.api.ecmwf.Ecmwf
import com.openmeteo.api.ecmwf.EcmwfHourly
import com.openmeteo.api.elevation.Elevation
import com.openmeteo.api.forecast.Forecast
import com.openmeteo.api.forecast.ForecastDaily
import com.openmeteo.api.forecast.ForecastHourly
import com.openmeteo.api.geocoding.GeocodingGet
import com.openmeteo.api.geocoding.GeocodingSearch
import com.openmeteo.api.gfs.Gfs
import com.openmeteo.api.gfs.GfsDaily
import com.openmeteo.api.gfs.GfsHourly
import com.openmeteo.api.historical.Historical
import com.openmeteo.api.historical.HistoricalDaily
import com.openmeteo.api.historical.HistoricalHourly
import com.openmeteo.api.marine.Marine
import com.openmeteo.api.marine.MarineDaily
import com.openmeteo.api.marine.MarineHourly
import kotlinx.serialization.SerialName

class OpenMeteo(
    override val latitude: Float = 0f,
    override val longitude: Float = 0f,
    val airQuality: Endpoint = Endpoint(AirQuality.context),
    val ecmwf: Endpoint = Endpoint(Ecmwf.context),
    val elevation: Endpoint = Endpoint(Elevation.context),
    val forecast: Endpoint = Endpoint(Forecast.context),
    val geocodingGet: Endpoint = Endpoint(GeocodingGet.context),
    val geocodingSearch: Endpoint = Endpoint(GeocodingSearch.context),
    val gfs: Endpoint = Endpoint(Gfs.context),
    val historical: Endpoint = Endpoint(Historical.context),
    val marine: Endpoint = Endpoint(Marine.context),
) : QueryCoordinates {

    constructor(coordinates: Pair<Float, Float>) : this(
        coordinates.first,
        coordinates.second,
    )

    constructor(city: City) : this(city.coordinates)

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

    operator fun invoke(query: Forecast.Query) =
        forecast.query<Forecast.Response>(query)

    operator fun invoke(query: GeocodingGet.Query) =
        geocodingGet.query<GeocodingGet.Response>(query)

    operator fun invoke(query: GeocodingSearch.Query) =
        geocodingSearch.query<GeocodingSearch.Response>(query)

    operator fun invoke(query: Gfs.Query) =
        gfs.query<Gfs.Response>(query)

    operator fun invoke(query: Historical.Query) =
        historical.query<Historical.Response>(query)

    operator fun invoke(query: Marine.Query) =
        marine.query<Marine.Response>(query)

    fun airQuality(
        hourly: Iterable<AirQualityHourly>? = null,
        domains: AirQualityDomains? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        AirQuality.Query(
            latitude, longitude, hourly, domains, timeZone,
            startDate, endDate, pastDays
        )
    )

    fun ecmwf(
        hourly: Iterable<EcmwfHourly>? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Ecmwf.Query(
            latitude, longitude, hourly, temperatureUnit,
            windSpeedUnit, precipitationUnit, timeZone, startDate, endDate, pastDays
        )
    )

    fun elevation(
        vararg coordinates: Pair<Float, Float> =
            arrayOf(this.latitude to this.longitude),
    ) = invoke(Elevation.Query(*coordinates))

    fun forecast(
        hourly: Iterable<ForecastHourly>? = null,
        daily: Iterable<ForecastDaily>? = null,
        currentWeather: Boolean? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Forecast.Query(
            latitude, longitude, hourly, daily, currentWeather,
            temperatureUnit, windSpeedUnit, precipitationUnit, timeZone, startDate,
            endDate, pastDays
        )
    )

    fun geocoding(
        id: Int,
    ) = invoke(GeocodingGet.Query(id))

    fun geocoding(
        name: String,
        count: Int? = null,
        language: String? = null,
    ) = invoke(GeocodingSearch.Query(name, count, language))

    fun gfs(
        hourly: Iterable<GfsHourly>? = null,
        daily: Iterable<GfsDaily>? = null,
        currentWeather: Boolean? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
        forecastDays: Int? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Gfs.Query(
            latitude, longitude, hourly, daily, currentWeather,
            temperatureUnit, windSpeedUnit, precipitationUnit, timeZone, startDate,
            endDate, pastDays, forecastDays
        )
    )

    fun historical(
        hourly: Iterable<HistoricalHourly>? = null,
        daily: Iterable<HistoricalDaily>? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Historical.Query(
            latitude, longitude, hourly, daily, temperatureUnit,
            windSpeedUnit, precipitationUnit, timeZone, startDate, endDate
        )
    )

    fun marine(
        hourly: Iterable<MarineHourly>? = null,
        daily: Iterable<MarineDaily>? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Marine.Query(
            latitude, longitude, hourly, daily, timeZone, startDate,
            endDate
        )
    )

}
