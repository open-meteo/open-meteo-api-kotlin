package com.openmeteo.api.geocoding.serials

import com.openmeteo.api.common.serials.TimeZoneSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class GeocodingGet(
    val id: Int,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    @Serializable(with = TimeZoneSerializer::class)
    val timezone: TimeZone,
    // https://www.geonames.org/export/codes.html enum?
    val feature_code: String,
    // https://en.wikipedia.org/wiki/List_of_FIPS_country_codes enum?
    val country_code: String,
    val country: String,
    val country_id: Int,
    val population: Int,
    val postcodes: Array<String>,
    val admin1: String? = null,
    val admin2: String? = null,
    val admin3: String? = null,
    val admin4: String? = null,
    val admin1_id: Int? = null,
    val admin2_id: Int? = null,
    val admin3_id: Int? = null,
    val admin4_id: Int? = null,
)