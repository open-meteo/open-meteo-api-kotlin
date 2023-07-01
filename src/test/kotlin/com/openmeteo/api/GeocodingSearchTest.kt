package com.openmeteo.api

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class GeocodingSearchTest {

    @Test
    fun `Berlin, Italian translation (Berlino, 2950159)`() {
        val query = GeocodingSearch.Query(
            name = "Berlino",
            count = 1,
            language = "it",
        )
        GeocodingSearch(query).getOrThrow().run {
            assertEquals(1, results.size)
            with (results[0]) {
                assertEquals(2950159, id)
                assertEquals("Berlino", name)

                assertEquals(52.52437f, latitude)
                assertEquals(13.41053f, longitude)
                assertEquals(74f, elevation)

                assertEquals("PPLC", featureCode)

                assertEquals("Germania", country)
                assertEquals(2921044, countryId)
                assertEquals("DE", countryCode)

                assertEquals("Europe/Berlin", timezone?.id)

                assertContentEquals(arrayOf("10967", "13347"), postcodes)

                assertEquals("Berlino", admin1)
                assertEquals(2950157, admin1Id)
                assertEquals(null, admin2)
                assertEquals(null, admin2Id)
                assertEquals("Berlin, Stadt", admin3)
                assertEquals(6547383, admin3Id)
                assertEquals("Berlin", admin4)
                assertEquals(6547539, admin4Id)
            }
        }
    }

}
