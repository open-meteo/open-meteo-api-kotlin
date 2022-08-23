package com.openmeteo.api.geocoding.response

import com.openmeteo.api.common.time.TimeZone
import kotlinx.serialization.Serializable

@Serializable
class GeocodingGet(
    val id: Int,
    val name: String, // #2
    private val _3: String? = null,
    val latitude: Float, // #4
    val longitude: Float, // #5
    val ranking: Float, // #6
    val elevation: Float, // #7
    // https://www.geonames.org/export/codes.html enum?
    val feature_code: String, // #8
    // https://en.wikipedia.org/wiki/List_of_FIPS_country_codes enum?
    val country_code: String, // #9
    val admin1_id: Int? = null, // #10
    val admin2_id: Int? = null, // #11
    val admin3_id: Int? = null, // #12
    val admin4_id: Int? = null, // #13
    val timezone: TimeZone, // #14
    val population: Int,  // #15
    private val _16: Int? = null,
    val postcodes: Array<String>, // #17
    val country_id: Int,
    val country: String,
    val admin1: String? = null,
    val admin2: String? = null,
    val admin3: String? = null,
    val admin4: String? = null,
)
