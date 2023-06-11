package com.openmeteo.api

import com.openmeteo.api.common.CellSelection
import com.openmeteo.api.common.Options
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.PrecipitationUnit
import com.openmeteo.api.common.units.TemperatureUnit
import com.openmeteo.api.common.units.Unit
import com.openmeteo.api.common.units.WindSpeedUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object ClimateChange : Endpoint(
    URL("https://marine-api.open-meteo.com/v1/marine")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    @Serializable
    open class Query(
        override val latitude: Float,
        override val longitude: Float,
        override val daily: String? = null,
        @SerialName("start_date")
        override val startDate: Date? = null,
        @SerialName("end_date")
        override val endDate: Date? = null,
        @SerialName("temperature_unit")
        override val temperatureUnit: TemperatureUnit? = null,
        @SerialName("windspeed_unit")
        override val windSpeedUnit: WindSpeedUnit? = null,
        @SerialName("precipitation_unit")
        override val precipitationUnit: PrecipitationUnit? = null,
        override val elevation: Float? = null,
        override val models: String? = null,
        @SerialName("disable_bias_correction")
        val disableBiasCorrection: Boolean? = null,
        override val cellSelection: CellSelection? = null,
        override val apikey: String? = null,
    ) : Q.Coordinate, Q.Elevation, Q.Daily, Q.TimeFormat, Q.DateRange, Q.Models,
        Q.TemperatureUnit, Q.WindSpeedUnit, Q.PrecipitationUnit, Q.CellSelection,
        Q.CommercialLicense

    @Serializable
    open class Response(
        override val latitude: Float,
        override val longitude: Float,
        override val utcOffsetSeconds: Int,
        override val timezone: Timezone,
        override val timezoneAbbreviation: String,
        override val generationtimeMs: Float,
        override val dailyUnits: Map<String, Unit> = mapOf(),
        @SerialName("daily")
        override val dailyValues: Map<String, Array<Double?>> = mapOf(),
    ) : R.Coordinate, R.GenerationTimed, R.TimeZone, R.Daily

    @Serializable
    object Models : Options.Models, Options.Listable<Models>() {
        const val CMCCCM2VHR4="CMCC_CM2_VHR4"
        const val FGOALSF3H="FGOALS_f3_H"
        const val HiRAMSITHR="HiRAM_SIT_HR"
        const val MRIAGCM32S="MRI_AGCM3_2_S"
        const val ECEarth3PHR="EC_Earth3P_HR"
        const val MPIESM12XR="MPI_ESM1_2_XR"
        const val NICAM168S="NICAM16_8S"
    }

    @Serializable
    object Daily : Options.Daily, Options.Listable<Daily>() {
        const val temperature2mMean="temperature_2m_mean"
        const val temperature2mMax="temperature_2m_max"
        const val temperature2mMin="temperature_2m_min"
        const val windspeed10mMean="windspeed_10m_mean"
        const val windspeed10mMax="windspeed_10m_max"
        const val cloudcoverMean="cloudcover_mean"
        const val shortwaveRadiationSum="shortwave_radiation_sum"
        const val relativeHumidity2mMean="relative_humidity_2m_mean"
        const val relativeHumidity2mMax="relative_humidity_2m_max"
        const val relativeHumidity2mMin="relative_humidity_2m_min"
        const val dewpoint2mMean="dewpoint_2m_mean"
        const val dewpoint2mMin="dewpoint_2m_min"
        const val dewpoint2mMax="dewpoint_2m_max"
        const val precipitationSum="precipitation_sum"
        const val rainSum="rain_sum"
        const val snowfallSum="snowfall_sum"
        const val pressureMslMean="pressure_msl_mean"
        const val soilMoisture0To10cmMean="soil_moisture_0_to_10cm_mean"
        const val et0FaoEvapotranspirationSum="et0_fao_evapotranspiration_sum"
        const val vaporPressureDeficitMean="vapor_pressure_deficit_mean"
        const val soilMoisture0To100cmMean="soil_moisture_0_to_100cm_mean"
        const val soilMoistureIndex0To100cmMean="soil_moisture_index_0_to_100cm_mean"
        const val soilMoistureIndex0To10cmMean="soil_moisture_index_0_to_10cm_mean"
        const val soilTemperature0To100cmMean="soil_temperature_0_to_100cm_mean"
    }

}
