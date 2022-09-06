package com.openmeteo.apix.geocoding

import com.openmeteo.apix.common.http.Endpoint
import com.openmeteo.apix.common.query.Query
import com.openmeteo.apix.common.time.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

open class GeocodingGet(
    val id: Int,
    context: URL = Companion.context,
) : Endpoint(context) {

    companion object {
        val context = URL("https://geocoding-api.open-meteo.com/v1/get")
    }

    @Serializable
    open class Response(
        val id: Int,
        val name: String, // #2
        private val _3: String? = null,
        val latitude: Float, // #4
        val longitude: Float, // #5
        val ranking: Float, // #6
        val elevation: Float, // #7
        // https://www.geonames.org/export/codes.html enum?
        @SerialName("feature_code")
        val featureCode: String, // #8
        // https://en.wikipedia.org/wiki/List_of_FIPS_country_codes enum?
        @SerialName("country_code")
        val countryCode: String, // #9
        @SerialName("admin1_id")
        val admin1Id: Int? = null, // #10
        @SerialName("admin2_id")
        val admin2Id: Int? = null, // #11
        @SerialName("admin3_id")
        val admin3Id: Int? = null, // #12
        @SerialName("admin4_id")
        val admin4Id: Int? = null, // #13
        val timezone: TimeZone, // #14
        val population: Int,  // #15
        private val _16: Int? = null,
        val postcodes: Array<String>, // #17
        @SerialName("country_id")
        val countryId: Int,
        val country: String,
        val admin1: String? = null,
        val admin2: String? = null,
        val admin3: String? = null,
        val admin4: String? = null,
    )

    operator fun invoke() = invoke<Response>()
    operator fun invoke(query: Query) = invoke<Response>(query)

}
