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
    override var latitude: Float = 0f,
    override var longitude: Float = 0f,
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

    private inline fun <reified T> separate(iterable: Iterable<Any>?) =
        iterable?.filterIsInstance<T>()?.ifEmpty { null }

    class AnyResponse(
        override val latitude: Float,
        override val longitude: Float,
        val elevation: Float? = null,
        @SerialName("utc_offset_seconds")
        override val utcOffsetSeconds: Int,
        @SerialName("timezone")
        override val timeZone: TimeZone,
        @SerialName("timezone_abbreviation")
        override val timeZoneAbbreviation: String,
        @SerialName("hourly_units")
        override val hourlyUnits: Map<out QueryHourly.Options, Unit> = emptyMap(),
        @SerialName("hourly")
        override val hourlyValues: Map<out QueryHourly.Options, Array<Double?>> = emptyMap(),
        @SerialName("daily_units")
        override val dailyUnits: Map<out QueryDaily.Options, Unit> = emptyMap(),
        @SerialName("daily")
        override val dailyValues: Map<out QueryDaily.Options, Array<Double?>> = emptyMap(),
    ) : ResponseCoordinates,
        ResponseHourly,
        ResponseDaily

    operator fun invoke(
        hourly: Iterable<QueryHourly.Options> = emptyList(),
        daily: Iterable<QueryDaily.Options> = emptyList(),
        timeZone: TimeZone? = TimeZone.auto.takeIf { daily.any() },
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
        forecastDays: Int? = null,
        domains: AirQualityDomains? = null,
        currentWeather: Boolean? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = runCatching {

        val airQualityHourly = separate<AirQualityHourly>(hourly)
        val airQualityResponse = airQualityHourly?.let {
            airQuality(
                airQualityHourly, domains, timeZone, startDate, endDate,
                pastDays, latitude, longitude
            ).getOrThrow()
        }

        val ecmwfHourly = separate<EcmwfHourly>(hourly)
        val ecmwfResponse = ecmwfHourly?.let {
            ecmwf(
                ecmwfHourly, temperatureUnit, windSpeedUnit, precipitationUnit,
                timeZone, startDate, endDate, pastDays, latitude, longitude
            ).getOrThrow()
        }

        val forecastHourly = separate<ForecastHourly>(hourly)
        val forecastDaily = separate<ForecastDaily>(daily)
        // if both are null return null, else return hourly *or* daily
        val forecastResponse = (forecastHourly ?: forecastDaily)?.let {
            forecast(
                forecastHourly, forecastDaily, currentWeather,
                temperatureUnit, windSpeedUnit, precipitationUnit, timeZone,
                startDate, endDate, pastDays, latitude, longitude
            ).getOrThrow()
        }

        val gfsHourly = separate<GfsHourly>(hourly)
        val gfsDaily = separate<GfsDaily>(daily)
        val gfsResponse = (gfsHourly ?: gfsDaily)?.let {
            gfs(
                gfsHourly, gfsDaily, currentWeather, temperatureUnit,
                windSpeedUnit, precipitationUnit, timeZone, startDate, endDate,
                pastDays, forecastDays, latitude, longitude
            ).getOrThrow()
        }

        val historicalHourly = separate<HistoricalHourly>(hourly)
        val historicalDaily = separate<HistoricalDaily>(daily)
        val historicalResponse = (historicalHourly ?: historicalDaily)?.let {
            historical(
                historicalHourly, historicalDaily, temperatureUnit,
                windSpeedUnit, precipitationUnit, timeZone, startDate, endDate,
                latitude, longitude
            ).getOrThrow()
        }

        val marineHourly = separate<MarineHourly>(hourly)
        val marineDaily = separate<MarineDaily>(daily)
        val marineResponse = (marineHourly ?: marineDaily)?.let {
            marine(
                marineHourly, marineDaily, timeZone, startDate, endDate,
                latitude, longitude
            ).getOrThrow()
        }

        val hourlyResponses: List<ResponseHourly> = listOfNotNull(
            airQualityResponse,
            ecmwfResponse,
            forecastResponse,
            gfsResponse,
            historicalResponse,
            marineResponse,
        )

        val dailyResponses: List<ResponseDaily> = listOfNotNull(
            forecastResponse,
            gfsResponse,
            historicalResponse,
            marineResponse,
        )

        val responses = (hourlyResponses + dailyResponses)
            // we don't want duplicates
            .toSet().toList()

        if (responses.isEmpty())
            throw Error("No request was performed! No valid option")

        val elevation = forecastResponse?.elevation
            ?: gfsResponse?.elevation

        val utcOffsetSeconds = hourlyResponses[0].utcOffsetSeconds
        val timeZone0 = hourlyResponses[0].timeZone
        val timeZoneAbbreviation = hourlyResponses[0].timeZoneAbbreviation

        val hourlyUnits = hourlyResponses
            .map { it.hourlyUnits }
            .reduce { acc, map -> acc + map }

        val hourlyValues = hourlyResponses
            .map { it.hourlyValues }
            .reduce { acc, map -> acc + map }

        val dailyUnits = dailyResponses
            .map { it.dailyUnits }
            .reduce { acc, map -> acc + map }

        val dailyValues = dailyResponses
            .map { it.dailyValues }
            .reduce { acc, map -> acc + map }



        AnyResponse(
            latitude,
            longitude,
            elevation,
            utcOffsetSeconds,
            timeZone0,
            timeZoneAbbreviation,
            hourlyUnits,
            hourlyValues,
            dailyUnits,
            dailyValues,
        )

    }

    operator fun invoke(
        vararg options: Query.Options,
        timeZone: TimeZone? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        pastDays: Int? = null,
        forecastDays: Int? = null,
        domains: AirQualityDomains? = null,
        currentWeather: Boolean? = null,
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        options.filterIsInstance<QueryHourly.Options>(),
        options.filterIsInstance<QueryDaily.Options>(),
        timeZone, startDate, endDate, pastDays, forecastDays, domains,
        currentWeather, temperatureUnit, windSpeedUnit, precipitationUnit,
        latitude, longitude,
    )

}
