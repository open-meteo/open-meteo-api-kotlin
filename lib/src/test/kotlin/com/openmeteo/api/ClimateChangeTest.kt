package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.units.Unit
import kotlin.test.Test
import kotlin.test.assertEquals

class ClimateChangeTest {

    @Test
    fun `Amsterdam, 1st January 1950 - 31st december 2050`() {
        val query = ClimateChange.Query(
            latitude = City.Amsterdam.latitude,
            longitude = City.Amsterdam.longitude,
            models = ClimateChange.Models { listOf(
                MRIAGCM32S, MPIESM12XR,
            ) },
            startDate = Date("1950-01-01"),
            endDate = Date("2050-12-31"),
            daily = ClimateChange.Daily { listOf(
                temperature2mMax
            ) },
        )
        ClimateChange(query).getOrThrow().run {
            assertEquals(52.40001f, latitude)
            assertEquals(4.900009f, longitude)
            assertEquals(0, utcOffsetSeconds)
            assertEquals("GMT", timezone.id)
            assertEquals("GMT", timezoneAbbreviation)
            assertEquals(17f, elevation)
            assertEquals(mapOf(
                "time" to Unit.UnixTime,
                "temperature_2m_max_MRI_AGCM3_2_S" to Unit.Celsius,
                "temperature_2m_max_MPI_ESM1_2_XR" to Unit.Celsius,
            ), dailyUnits)
            assertEquals(-631152000.0, dailyValues["time"]!![0])
            assertEquals(2556057600.0, dailyValues["time"]!!.last())
            assertEquals(2.8, dailyValues["temperature_2m_max_MRI_AGCM3_2_S"]!![0])
            assertEquals(12.0, dailyValues["temperature_2m_max_MRI_AGCM3_2_S"]!!.last())
            assertEquals(6.9, dailyValues["temperature_2m_max_MPI_ESM1_2_XR"]!![0])
            assertEquals(7.9, dailyValues["temperature_2m_max_MPI_ESM1_2_XR"]!!.last())
        }
    }

}
