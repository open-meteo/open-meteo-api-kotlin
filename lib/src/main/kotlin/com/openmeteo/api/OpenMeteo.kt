package com.openmeteo.api

import com.openmeteo.api.airquality.AirQuality
import com.openmeteo.api.airquality.AirQualityDomains
import com.openmeteo.api.airquality.AirQualityHourly
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.query.QueryCoordinates
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
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
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<AirQualityHourly>? = null,
        domains: AirQualityDomains? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
    ) = invoke(AirQuality.Query(latitude, longitude, hourly, domains, timeZone,
        startDate, endDate, pastDays))

    fun ecmwf(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<EcmwfHourly>? = null,
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
        vararg coordinates: Pair<Float, Float> =
            arrayOf(this.latitude to this.longitude),
    ) = invoke(Elevation.Query(*coordinates))

    fun forecast(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
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
    ) = invoke(Forecast.Query(latitude, longitude, hourly, daily, currentWeather,
        temperatureUnit, windSpeedUnit, precipitationUnit, timeZone, startDate,
        endDate, pastDays))

    fun geocoding(
        id: Int,
    ) = invoke(GeocodingGet.Query(id))

    fun geocoding(
        name: String,
        count: Int? = null,
        language: String? = null,
    ) = invoke(GeocodingSearch.Query(name, count, language))

    fun gfs(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
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
    ) = invoke(Gfs.Query(latitude, longitude, hourly, daily, currentWeather,
        temperatureUnit, windSpeedUnit, precipitationUnit, timeZone, startDate,
        endDate, pastDays, forecastDays))

    fun historical(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<HistoricalHourly>? = null,
        daily: Iterable<HistoricalDaily>? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
    ) = invoke(Historical.Query(latitude, longitude, hourly, daily, temperatureUnit,
        windSpeedUnit, precipitationUnit, timeZone, startDate, endDate))

    fun marine(
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
        hourly: Iterable<MarineHourly>? = null,
        daily: Iterable<MarineDaily>? = null,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
    ) = invoke(Marine.Query(latitude, longitude, hourly, daily, timeZone, startDate,
        endDate))

}
