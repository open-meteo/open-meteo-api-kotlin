package com.openmeteo.api.geocoding

import kotlinx.serialization.ExperimentalSerializationApi
import java.util.*
import kotlin.test.*

class GeocodingGetEndpointTest {
    companion object {
        val geocodingGetEndpoint = GeocodingGetEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun Berlin() {
        val response = geocodingGetEndpoint(
            2950159,
        ).getOrThrow()
        with(response) {
            assertEquals(id, 2950159)
            assertEquals(name, "Berlin")
            assertEquals(latitude, 52.52437f)
            assertEquals(longitude, 13.41053f)
            assertEquals(ranking, 1.4000001f)
            assertEquals(elevation, 74f)
            assertEquals(feature_code, "PPLC")
            assertEquals(country_id, 2921044)
            assertEquals(country, "Germany")
            assertEquals(country_code, "DE")
            assertEquals(admin1_id, 2950157)
            assertEquals(admin1, "Land Berlin")
            assertNull(admin2_id)
            assertNull(admin2)
            assertEquals(admin3_id, 6547383)
            assertEquals(admin3, "Berlin, Stadt")
            assertEquals(admin4_id, 6547539)
            assertEquals(admin4, "Berlin")
            assertEquals(timezone.id, "Europe/Berlin")
            // assertNotNull(response.population)
            assertEquals(postcodes[0], "10967")
            assertEquals(postcodes[1], "13347")
        }
    }
}
