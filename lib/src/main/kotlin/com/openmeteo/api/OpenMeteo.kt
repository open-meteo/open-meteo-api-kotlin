package com.openmeteo.api

import com.openmeteo.api.airquality.AirQuality
import com.openmeteo.api.airquality.AirQualityDomains
import com.openmeteo.api.airquality.AirQualityHourly
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.query.*
import com.openmeteo.api.common.response.ResponseCoordinates
import com.openmeteo.api.common.response.ResponseCurrentWeather
import com.openmeteo.api.common.response.ResponseDaily
import com.openmeteo.api.common.response.ResponseHourly
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Unit
import com.openmeteo.api.common.units.WindSpeedUnit
import com.openmeteo.api.dwd.Dwd
import com.openmeteo.api.dwd.DwdHourly
import com.openmeteo.api.dwd.DwdDaily
import com.openmeteo.api.ecmwf.Ecmwf
import com.openmeteo.api.ecmwf.EcmwfHourly
import com.openmeteo.api.elevation.Elevation
import com.openmeteo.api.flood.Flood
import com.openmeteo.api.flood.FloodDaily
import com.openmeteo.api.forecast.Forecast
import com.openmeteo.api.forecast.ForecastDaily
import com.openmeteo.api.forecast.ForecastHourly
import com.openmeteo.api.gem.Gem
import com.openmeteo.api.gem.GemDaily
import com.openmeteo.api.gem.GemHourly
import com.openmeteo.api.geocoding.GeocodingGet
import com.openmeteo.api.geocoding.GeocodingSearch
import com.openmeteo.api.gfs.Gfs
import com.openmeteo.api.gfs.GfsDaily
import com.openmeteo.api.gfs.GfsHourly
import com.openmeteo.api.historical.Historical
import com.openmeteo.api.historical.HistoricalDaily
import com.openmeteo.api.historical.HistoricalHourly
import com.openmeteo.api.jma.Jma
import com.openmeteo.api.jma.JmaDaily
import com.openmeteo.api.jma.JmaHourly
import com.openmeteo.api.marine.Marine
import com.openmeteo.api.marine.MarineDaily
import com.openmeteo.api.marine.MarineHourly
import com.openmeteo.api.meteofrance.MeteoFrance
import com.openmeteo.api.meteofrance.MeteoFranceHourly
import com.openmeteo.api.meteofrance.MeteoFranceDaily
import com.openmeteo.api.metno.MetNo
import com.openmeteo.api.metno.MetNoHourly
import kotlinx.serialization.SerialName

class OpenMeteo(
    override var latitude: Float = 0f,
    override var longitude: Float = 0f,
    val endpoints: Endpoints = Endpoints(),
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

    class Endpoints(
        val airQuality: Endpoint = Endpoint(AirQuality.context),
        val dwd: Endpoint = Endpoint(Dwd.context),
        val ecmwf: Endpoint = Endpoint(Ecmwf.context),
        val elevation: Endpoint = Endpoint(Elevation.context),
        val flood: Endpoint = Endpoint(Flood.context),
        val forecast: Endpoint = Endpoint(Forecast.context),
        val gem: Endpoint = Endpoint(Gem.context),
        val geocodingGet: Endpoint = Endpoint(GeocodingGet.context),
        val geocodingSearch: Endpoint = Endpoint(GeocodingSearch.context),
        val gfs: Endpoint = Endpoint(Gfs.context),
        val historical: Endpoint = Endpoint(Historical.context),
        val jma: Endpoint = Endpoint(Jma.context),
        val marine: Endpoint = Endpoint(Marine.context),
        val meteoFrance: Endpoint = Endpoint(MeteoFrance.context),
        val metNo: Endpoint = Endpoint(MetNo.context),
    )

    var coordinates
        get() =
            latitude to longitude
        set(pair) =
            pair.run {
                latitude = first
                longitude = second
            }

    operator fun invoke(query: AirQuality.Query) =
        endpoints.airQuality.query<AirQuality.Response>(query)

    operator fun invoke(query: Dwd.Query) =
        endpoints.dwd.query<Dwd.Response>(query)

    operator fun invoke(query: Ecmwf.Query) =
        endpoints.ecmwf.query<Ecmwf.Response>(query)

    operator fun invoke(query: Elevation.Query) =
        endpoints.elevation.query<Elevation.Response>(query)

    operator fun invoke(query: Flood.Query) =
        endpoints.flood.query<Flood.Response>(query)

    operator fun invoke(query: Forecast.Query) =
        endpoints.forecast.query<Forecast.Response>(query)

    operator fun invoke(query: Gem.Query) =
        endpoints.gem.query<Gem.Response>(query)

    operator fun invoke(query: GeocodingGet.Query) =
        endpoints.geocodingGet.query<GeocodingGet.Response>(query)

    operator fun invoke(query: GeocodingSearch.Query) =
        endpoints.geocodingSearch.query<GeocodingSearch.Response>(query)

    operator fun invoke(query: Gfs.Query) =
        endpoints.gfs.query<Gfs.Response>(query)

    operator fun invoke(query: Historical.Query) =
        endpoints.historical.query<Historical.Response>(query)

    operator fun invoke(query: Jma.Query) =
        endpoints.jma.query<Jma.Response>(query)

    operator fun invoke(query: Marine.Query) =
        endpoints.marine.query<Marine.Response>(query)

    operator fun invoke(query: MeteoFrance.Query) =
        endpoints.meteoFrance.query<MeteoFrance.Response>(query)

    operator fun invoke(query: MetNo.Query) =
        endpoints.metNo.query<MetNo.Response>(query)

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

    fun dwd(
        hourly: Iterable<DwdHourly>? = null,
        daily: Iterable<DwdDaily>? = null,
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
        Dwd.Query(
            latitude, longitude, hourly, daily, currentWeather,
            temperatureUnit, windSpeedUnit, precipitationUnit, timeZone, startDate,
            endDate, pastDays
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

    fun flood(
        daily: Iterable<FloodDaily>? = null,
        startDate: Date? = null,
        endDate: Date? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Flood.Query(latitude, longitude, daily, startDate, endDate)
    )

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

    fun gem(
        hourly: Iterable<GemHourly>? = null,
        daily: Iterable<GemDaily>? = null,
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
        Gem.Query(
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

    fun jma(
        hourly: Iterable<JmaHourly>? = null,
        daily: Iterable<JmaDaily>? = null,
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
        Jma.Query(
            latitude, longitude, hourly, daily, currentWeather, temperatureUnit,
            windSpeedUnit, precipitationUnit, timeZone, startDate, endDate,
            pastDays
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

    fun meteoFrance(
        hourly: Iterable<MeteoFranceHourly>? = null,
        daily: Iterable<MeteoFranceDaily>? = null,
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
        MeteoFrance.Query(
            latitude, longitude, hourly, daily, currentWeather,
            temperatureUnit, windSpeedUnit, precipitationUnit, timeZone, startDate,
            endDate, pastDays
        )
    )

    fun metNo(
        hourly: Iterable<MetNoHourly>? = null,
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
        MetNo.Query(
            latitude, longitude, hourly, temperatureUnit, windSpeedUnit,
            precipitationUnit, timeZone, startDate, endDate, pastDays
        )
    )

    fun currentWeather(
        temperatureUnit: TemperatureUnit? = null,
        windSpeedUnit: WindSpeedUnit? = null,
        precipitationUnit: PrecipitationUnit? = null,
        timeZone: TimeZone? = null,
        latitude: Float = this.latitude,
        longitude: Float = this.longitude,
    ) = invoke(
        Forecast.Query(
            latitude, longitude,null, null,true,
            temperatureUnit, windSpeedUnit, precipitationUnit, timeZone
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
        @SerialName("current_weather")
        override val currentWeather: ResponseCurrentWeather.CurrentWeather? = null
    ) : ResponseCoordinates,
        ResponseHourly,
        ResponseDaily,
        ResponseCurrentWeather

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

        val dwdHourly = separate<DwdHourly>(hourly)
        val dwdDaily = separate<DwdDaily>(daily)
        // if both are null return null, else return hourly *or* daily
        val dwdResponse = (dwdHourly ?: dwdDaily)?.let {
            dwd(
                dwdHourly, dwdDaily, currentWeather,
                temperatureUnit, windSpeedUnit, precipitationUnit, timeZone,
                startDate, endDate, pastDays, latitude, longitude
            ).getOrThrow()
        }

        val ecmwfHourly = separate<EcmwfHourly>(hourly)
        val ecmwfResponse = ecmwfHourly?.let {
            ecmwf(
                ecmwfHourly, temperatureUnit, windSpeedUnit, precipitationUnit,
                timeZone, startDate, endDate, pastDays, latitude, longitude
            ).getOrThrow()
        }

        val floodDaily = separate<FloodDaily>(daily)
        val floodResponse = floodDaily?.let {
            flood(
                floodDaily, startDate, endDate, latitude, longitude
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

        val gemHourly = separate<GemHourly>(hourly)
        val gemDaily = separate<GemDaily>(daily)
        // if both are null return null, else return hourly *or* daily
        val gemResponse = (gemHourly ?: gemDaily)?.let {
            gem(
                gemHourly, gemDaily, currentWeather,
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

        val jmaHourly = separate<JmaHourly>(hourly)
        val jmaDaily = separate<JmaDaily>(daily)
        // if both are null return null, else return hourly *or* daily
        val jmaResponse = (jmaHourly ?: jmaDaily)?.let {
            jma(
                jmaHourly, jmaDaily, currentWeather,
                temperatureUnit, windSpeedUnit, precipitationUnit, timeZone,
                startDate, endDate, pastDays, latitude, longitude
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

        val meteoFranceHourly = separate<MeteoFranceHourly>(hourly)
        val meteoFranceDaily = separate<MeteoFranceDaily>(daily)
        val meteoFranceResponse = (meteoFranceHourly ?: meteoFranceDaily)?.let {
            meteoFrance(
                meteoFranceHourly, meteoFranceDaily, currentWeather,
                temperatureUnit, windSpeedUnit, precipitationUnit, timeZone,
                startDate, endDate, pastDays, latitude, longitude
            ).getOrThrow()
        }

        val metNoHourly = separate<MetNoHourly>(daily)
        val metNoResponse = metNoHourly?.let {
            metNo(
                metNoHourly, temperatureUnit, windSpeedUnit, precipitationUnit,
                timeZone, startDate, endDate, pastDays, latitude, longitude
            ).getOrThrow()
        }

        val hourlyResponses: List<ResponseHourly> = listOfNotNull(
            airQualityResponse,
            dwdResponse,
            ecmwfResponse,
            forecastResponse,
            gemResponse,
            gfsResponse,
            historicalResponse,
            jmaResponse,
            marineResponse,
            meteoFranceResponse,
            metNoResponse,
        )

        val dailyResponses: List<ResponseDaily> = listOfNotNull(
            dwdResponse,
            floodResponse,
            forecastResponse,
            gemResponse,
            gfsResponse,
            historicalResponse,
            jmaResponse,
            marineResponse,
            meteoFranceResponse,
        )

        val responses = (hourlyResponses + dailyResponses)
            // we don't want duplicates
            .toSet().toList()

        if (responses.isEmpty())
            throw Error("No request was performed! No valid option")

        val elevation = dwdResponse?.elevation
            ?: forecastResponse?.elevation
            ?: gfsResponse?.elevation
            ?: meteoFranceResponse?.elevation

        val currentWeather0 = dwdResponse?.currentWeather
            ?: gemResponse?.currentWeather
            ?: forecastResponse?.currentWeather
            ?: gfsResponse?.currentWeather
            ?: meteoFranceResponse?.currentWeather

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
            currentWeather0,
        )

    }

    operator fun invoke(
        vararg options: Query.Options,
        timeZone: TimeZone? =
            TimeZone.auto.takeIf { options.any { it is QueryDaily.Options } },
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
