package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.units.Units
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class HistoricalTest {

    @Test
    fun `Amsterdam, 1st January 2020, no models`() {
        val query = Historical.Query(
            latitude = City.Amsterdam.latitude,
            longitude = City.Amsterdam.longitude,
            hourly = Historical.Hourly { listOf(
                temperature2m
            ) },
            startDate = Date("2020-01-01"),
            endDate = Date("2020-01-01"),
        )
        Historical(query)
            .getOrThrow().run {
                assertEquals(52.40001f, latitude)
                assertEquals(4.900009f, longitude)
                assertEquals(0, utcOffsetSeconds)
                assertEquals("GMT", timezone.id)
                assertEquals("GMT", timezoneAbbreviation)
                assertEquals(17f, elevation)
                assert(hourlyUnits == mapOf(
                        "time" to Units.UnixTime,
                        "temperature_2m" to Units.Celsius,
                    )
                )
                assertContains(hourlyValues, "time")
                assertContentEquals(arrayOf(
                    1577836800.0,
                    1577840400.0,
                    1577844000.0,
                    1577847600.0,
                    1577851200.0,
                    1577854800.0,
                    1577858400.0,
                    1577862000.0,
                    1577865600.0,
                    1577869200.0,
                    1577872800.0,
                    1577876400.0,
                    1577880000.0,
                    1577883600.0,
                    1577887200.0,
                    1577890800.0,
                    1577894400.0,
                    1577898000.0,
                    1577901600.0,
                    1577905200.0,
                    1577908800.0,
                    1577912400.0,
                    1577916000.0,
                    1577919600.0
                ), hourlyValues["time"])
                assertContains(hourlyValues, "temperature_2m")
                assertContentEquals(arrayOf(
                    2.0,
                    1.9,
                    1.6,
                    1.5,
                    1.2,
                    1.0,
                    0.7,
                    0.5,
                    0.5,
                    1.0,
                    1.6,
                    2.4,
                    3.1,
                    3.5,
                    3.4,
                    3.2,
                    2.5,
                    2.1,
                    2.0,
                    1.6,
                    1.9,
                    2.1,
                    2.2,
                    2.3,
                ), hourlyValues["temperature_2m"])
            }
    }



    @Test
    fun `Amsterdam, 1st January 2020, with models`() {
        val query = Historical.Query(
            latitude = City.Amsterdam.latitude,
            longitude = City.Amsterdam.longitude,
            hourly = Historical.Hourly { listOf(
                temperature2m
            ) },
            startDate = Date("2020-01-01"),
            endDate = Date("2020-01-01"),
            models = Historical.Models { listOf(
                era5, cerra
            ) }
        )
        Historical(query).getOrThrow().run {
                assertEquals(52.25f, latitude)
            assertEquals(5f, longitude)
            assertEquals(0, utcOffsetSeconds)
            assertEquals("GMT", timezone.id)
            assertEquals("GMT", timezoneAbbreviation)
            assertEquals(17f, elevation)
            assert(hourlyUnits == mapOf(
                    "time" to Units.UnixTime,
                    "temperature_2m_era5" to Units.Celsius,
                    "temperature_2m_cerra" to Units.Celsius,
                )
            )
            assertContains(hourlyValues, "time")
            assertContentEquals(arrayOf(
                1577836800.0,
                1577840400.0,
                1577844000.0,
                1577847600.0,
                1577851200.0,
                1577854800.0,
                1577858400.0,
                1577862000.0,
                1577865600.0,
                1577869200.0,
                1577872800.0,
                1577876400.0,
                1577880000.0,
                1577883600.0,
                1577887200.0,
                1577890800.0,
                1577894400.0,
                1577898000.0,
                1577901600.0,
                1577905200.0,
                1577908800.0,
                1577912400.0,
                1577916000.0,
                1577919600.0
            ), hourlyValues["time"])
            assertContains(hourlyValues, "temperature_2m_era5")
            assertContentEquals(
                arrayOf(1.9, 1.8, 1.5, 1.5, 1.2, 1.2, 1.1, 0.8, 0.5, 0.5, 0.6, 1.0, 1.3, 1.4, 1.2, 1.5, 0.9, 0.6, 0.4, 0.3, 0.6, 1.1, 1.4, 1.5),
                hourlyValues["temperature_2m_era5"]
            )
            assertContains(hourlyValues, "temperature_2m_cerra")
            assertContentEquals(
                arrayOf(1.1, 0.6, 0.1, -0.3, 0.0, 0.3, 0.9, 1.1, 1.2, 1.4, 1.7, 2.0, 2.2, 2.5, 2.4, 2.2, 1.7, 1.2, 0.5, 0.1, -0.1, -0.1, 0.2, 0.1),
                hourlyValues["temperature_2m_cerra"]
            )
        }
    }

}
