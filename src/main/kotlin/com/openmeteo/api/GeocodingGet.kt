package com.openmeteo.api

import com.openmeteo.api.common.http.ContentFormat
import com.openmeteo.api.common.http.Endpoint
import com.openmeteo.api.common.time.Timezone
import kotlinx.serialization.Serializable
import java.net.URL
import com.openmeteo.api.common.Response as R
import com.openmeteo.api.common.query.Query as Q

object GeocodingGet : Endpoint(
    URL("https://geocoding-api.open-meteo.com/v1/get")
) {

    operator fun invoke(query: Query, context: URL = this.context) =
        query<Response, Query>(query, context)

    inline operator fun invoke(
        id: Int,
        apikey: String? = null,
        context: URL = this.context,
        query: Query.() -> Unit = {},
    ) = Query(id, apikey = apikey).let {
        it.query()
        this(it, context)
    }

    @Serializable
    open class Query(
        var id: Int,
        var language: String? = null,
        override var apikey: String? = null,
        override var format: ContentFormat? = ContentFormat.ProtoBuf,
    ) : Q.ContentFormat, Q.CommercialLicense

    @Serializable
    open class Response(                            // Proto order number
        val id: Int,                                // #01
        val name: String,                           // #02
        private val _3: String? = null,             // #03
        override val latitude: Float,               // #04
        override val longitude: Float,              // #05
        val ranking: Float? = null,                 // #06
        override val elevation: Float,              // #07
        // https://www.geonames.org/export/codes.html enum?
        val featureCode: String,                    // #08
        // https://en.wikipedia.org/wiki/List_of_FIPS_country_codes enum?
        val countryCode: String,                    // #09
        val admin1Id: Int? = null,                  // #10
        val admin2Id: Int? = null,                  // #11
        val admin3Id: Int? = null,                  // #12
        val admin4Id: Int? = null,                  // #13
        val timezone: Timezone? = null,             // #14
        val population: Int? = null,                // #15
        private val alternativeNames: Int? = null,  // #16
        val postcodes: Array<String>,               // #17
        val countryId: Int,                         // #18
        val country: String,                        // #19
        val admin1: String? = null,                 // #20
        val admin2: String? = null,                 // #21
        val admin3: String? = null,                 // #22
        val admin4: String? = null,                 // #23
    ) : R.Coordinate, R.Elevation

}
