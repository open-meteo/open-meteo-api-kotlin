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
    val timeZone: TimeZone,
    // https://www.geonames.org/export/codes.html enum?
    @SerialName("feature_code")
    val featureCode: String,
    // https://en.wikipedia.org/wiki/List_of_FIPS_country_codes enum?
    @SerialName("country_code")
    val countryCode: String,
    val country: String,
    @SerialName("country_id")
    val countryId: Int,
    val population: Int,
    val postCodes: Array<String>,
    val admin1: String? = null,
    val admin2: String? = null,
    val admin3: String? = null,
    val admin4: String? = null,
    val admin1Id: Int? = null,
    val admin2Id: Int? = null,
    val admin3Id: Int? = null,
    val admin4Id: Int? = null,
)