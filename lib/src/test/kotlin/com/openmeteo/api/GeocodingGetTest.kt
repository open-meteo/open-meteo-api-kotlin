package com.openmeteo.api

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class GeocodingGetTest {

    @Test
    fun `2950159 (Berlin)`() {
        val query = GeocodingGet.Query(2950159)
        GeocodingGet(query).getOrThrow().run {
            assertEquals(2950159, id)
            assertEquals("Berlin", name)

            assertEquals(52.52437f, latitude)
            assertEquals(13.41053f, longitude)
            assertEquals(74f, elevation)

            assertEquals("PPLC", featureCode)

            assertEquals("Germany", country)
            assertEquals(2921044, countryId)
            assertEquals("DE", countryCode)

            assertEquals("Europe/Berlin", timezone?.id)

            assertContentEquals(arrayOf("10967", "13347"), postcodes)

            assertEquals("Land Berlin", admin1)
            assertEquals(2950157, admin1Id)
            assertEquals(null, admin2)
            assertEquals(null, admin2Id)
            assertEquals("Berlin, Stadt", admin3)
            assertEquals(6547383, admin3Id)
            assertEquals("Berlin", admin4)
            assertEquals(6547539, admin4Id)
        }
    }

    @Test
    fun `2921044 (Germany)`() {
        val query = GeocodingGet.Query(2921044)
        GeocodingGet(query).getOrThrow().run {
            assertEquals(2921044, id)
            assertEquals("Germany", name)

            assertEquals(51.5f, latitude)
            assertEquals(10.5f, longitude)
            assertEquals(303f, elevation)

            assertEquals("PCLI", featureCode)

            assertEquals("Germany", country)
            assertEquals(2921044, countryId)
            assertEquals("DE", countryCode)
        }
    }

}
