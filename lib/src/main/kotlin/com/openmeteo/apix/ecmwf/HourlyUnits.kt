package com.openmeteo.apix.ecmwf

import com.openmeteo.apix.common.response.ResponseHourly
import com.openmeteo.apix.common.time.TimeFormat
import com.openmeteo.apix.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class HourlyUnits(
    override val time: TimeFormat,
    @SerialName("pressure_msl")
    val pressureMsl: Unit? = null,
    @SerialName("surface_air_pressure")
    val surfaceAirPressure: Unit? = null,
    @SerialName("skin_temperature")
    val skinTemperature: Unit? = null,
    @SerialName("soil_temperature_0_to_7cm")
    val soilTemperature0To7cm: Unit? = null,
    @SerialName("total_column_integrated_water_vapour")
    val totalColumnIntegratedWaterVapour: Unit? = null,
    @SerialName("temperature_2m")
    val temperature2m: Unit? = null,
    @SerialName("temperature_1000hPa")
    val temperature1000hPa: Unit? = null,
    @SerialName("temperature_925hPa")
    val temperature925hPa: Unit? = null,
    @SerialName("temperature_850hPa")
    val temperature850hPa: Unit? = null,
    @SerialName("temperature_700hPa")
    val temperature700hPa: Unit? = null,
    @SerialName("temperature_500hPa")
    val temperature500hPa: Unit? = null,
    @SerialName("temperature_300hPa")
    val temperature300hPa: Unit? = null,
    @SerialName("temperature_250hPa")
    val temperature250hPa: Unit? = null,
    @SerialName("temperature_200hPa")
    val temperature200hPa: Unit? = null,
    @SerialName("temperature_50hPa")
    val temperature50hPa: Unit? = null,
    @SerialName("geopotential_height_1000hPa")
    val geopotentialHeight1000hPa: Unit? = null,
    @SerialName("geopotential_height_925hPa")
    val geopotentialHeight925hPa: Unit? = null,
    @SerialName("geopotential_height_850hPa")
    val geopotentialHeight850hPa: Unit? = null,
    @SerialName("geopotential_height_700hPa")
    val geopotentialHeight700hPa: Unit? = null,
    @SerialName("geopotential_height_500hPa")
    val geopotentialHeight500hPa: Unit? = null,
    @SerialName("geopotential_height_300hPa")
    val geopotentialHeight300hPa: Unit? = null,
    @SerialName("geopotential_height_250hPa")
    val geopotentialHeight250hPa: Unit? = null,
    @SerialName("geopotential_height_200hPa")
    val geopotentialHeight200hPa: Unit? = null,
    @SerialName("geopotential_height_50hPa")
    val geopotentialHeight50hPa: Unit? = null,
    @SerialName("windspeed_10m")
    val windspeed10m: Unit? = null,
    @SerialName("windspeed_1000hPa")
    val windspeed1000hPa: Unit? = null,
    @SerialName("windspeed_925hPa")
    val windspeed925hPa: Unit? = null,
    @SerialName("windspeed_850hPa")
    val windspeed850hPa: Unit? = null,
    @SerialName("windspeed_700hPa")
    val windspeed700hPa: Unit? = null,
    @SerialName("windspeed_500hPa")
    val windspeed500hPa: Unit? = null,
    @SerialName("windspeed_300hPa")
    val windspeed300hPa: Unit? = null,
    @SerialName("windspeed_250hPa")
    val windspeed250hPa: Unit? = null,
    @SerialName("windspeed_200hPa")
    val windspeed200hPa: Unit? = null,
    @SerialName("windspeed_50hPa")
    val windspeed50hPa: Unit? = null,
    @SerialName("winddirection_10m")
    val winddirection10m: Unit? = null,
    @SerialName("winddirection_1000hPa")
    val winddirection1000hPa: Unit? = null,
    @SerialName("winddirection_925hPa")
    val winddirection925hPa: Unit? = null,
    @SerialName("winddirection_850hPa")
    val winddirection850hPa: Unit? = null,
    @SerialName("winddirection_700hPa")
    val winddirection700hPa: Unit? = null,
    @SerialName("winddirection_500hPa")
    val winddirection500hPa: Unit? = null,
    @SerialName("winddirection_300hPa")
    val winddirection300hPa: Unit? = null,
    @SerialName("winddirection_250hPa")
    val winddirection250hPa: Unit? = null,
    @SerialName("winddirection_200hPa")
    val winddirection200hPa: Unit? = null,
    @SerialName("winddirection_50hPa")
    val winddirection50hPa: Unit? = null,
    @SerialName("relative_humidity_1000hPa")
    val relativeHumidity1000hPa: Unit? = null,
    @SerialName("relative_humidity_925hPa")
    val relativeHumidity925hPa: Unit? = null,
    @SerialName("relative_humidity_850hPa")
    val relativeHumidity850hPa: Unit? = null,
    @SerialName("relative_humidity_700hPa")
    val relativeHumidity700hPa: Unit? = null,
    @SerialName("relative_humidity_500hPa")
    val relativeHumidity500hPa: Unit? = null,
    @SerialName("relative_humidity_300hPa")
    val relativeHumidity300hPa: Unit? = null,
    @SerialName("relative_humidity_250hPa")
    val relativeHumidity250hPa: Unit? = null,
    @SerialName("relative_humidity_200hPa")
    val relativeHumidity200hPa: Unit? = null,
    @SerialName("relative_humidity_50hPa")
    val relativeHumidity50hPa: Unit? = null,
    @SerialName("specific_humidity_1000hPa")
    val specificHumidity1000hPa: Unit? = null,
    @SerialName("specific_humidity_925hPa")
    val specificHumidity925hPa: Unit? = null,
    @SerialName("specific_humidity_850hPa")
    val specificHumidity850hPa: Unit? = null,
    @SerialName("specific_humidity_700hPa")
    val specificHumidity700hPa: Unit? = null,
    @SerialName("specific_humidity_500hPa")
    val specificHumidity500hPa: Unit? = null,
    @SerialName("specific_humidity_300hPa")
    val specificHumidity300hPa: Unit? = null,
    @SerialName("specific_humidity_250hPa")
    val specificHumidity250hPa: Unit? = null,
    @SerialName("specific_humidity_200hPa")
    val specificHumidity200hPa: Unit? = null,
    @SerialName("specific_humidity_50hPa")
    val specificHumidity50hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_1000hPa")
    val atmosphereRelativeVorticity1000hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_925hPa")
    val atmosphereRelativeVorticity925hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_850hPa")
    val atmosphereRelativeVorticity850hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_700hPa")
    val atmosphereRelativeVorticity700hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_500hPa")
    val atmosphereRelativeVorticity500hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_300hPa")
    val atmosphereRelativeVorticity300hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_250hPa")
    val atmosphereRelativeVorticity250hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_200hPa")
    val atmosphereRelativeVorticity200hPa: Unit? = null,
    @SerialName("atmosphere_relative_vorticity_50hPa")
    val atmosphereRelativeVorticity50hPa: Unit? = null,
    @SerialName("divergence_of_wind_1000hPa")
    val divergenceOfWind1000hPa: Unit? = null,
    @SerialName("divergence_of_wind_925hPa")
    val divergenceOfWind925hPa: Unit? = null,
    @SerialName("divergence_of_wind_850hPa")
    val divergenceOfWind850hPa: Unit? = null,
    @SerialName("divergence_of_wind_700hPa")
    val divergenceOfWind700hPa: Unit? = null,
    @SerialName("divergence_of_wind_500hPa")
    val divergenceOfWind500hPa: Unit? = null,
    @SerialName("divergence_of_wind_300hPa")
    val divergenceOfWind300hPa: Unit? = null,
    @SerialName("divergence_of_wind_250hPa")
    val divergenceOfWind250hPa: Unit? = null,
    @SerialName("divergence_of_wind_200hPa")
    val divergenceOfWind200hPa: Unit? = null,
    @SerialName("divergence_of_wind_50hPa")
    val divergenceOfWind50hPa: Unit? = null,
) : ResponseHourly.Units
