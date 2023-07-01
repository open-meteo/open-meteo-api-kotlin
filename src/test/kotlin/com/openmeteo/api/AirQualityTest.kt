package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.units.Units
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class AirQualityTest {

    @Test
    fun `Amsterdam, 1st January 2023, cams Europe domain`() {
        val query = AirQuality.Query(
            latitude = City.Amsterdam.latitude,
            longitude = City.Amsterdam.longitude,
            hourly = AirQuality.Hourly { listOf(
                pm25, usAqi
            ) },
            startDate = Date("2023-01-01"),
            endDate = Date("2023-01-01"),
        )
        AirQuality(query).getOrThrow().run {
                assertEquals(52.35f, latitude)
            assertEquals(4.8500004f, longitude)
            assertEquals(0, utcOffsetSeconds)
            assertEquals("GMT", timezone.id)
            assertEquals("GMT", timezoneAbbreviation)
            assert(hourlyUnits == mapOf(
                    "time" to Units.UnixTime,
                    AirQuality.Hourly.pm25 to Units.MicroGramsPerCubeMeter,
                    AirQuality.Hourly.usAqi to Units.UnitedStatesAirQualityIndex,
                )
            )
            assertContains(hourlyValues, "time")
            assertContentEquals(arrayOf(
                1672531200.0,
                1672534800.0,
                1672538400.0,
                1672542000.0,
                1672545600.0,
                1672549200.0,
                1672552800.0,
                1672556400.0,
                1672560000.0,
                1672563600.0,
                1672567200.0,
                1672570800.0,
                1672574400.0,
                1672578000.0,
                1672581600.0,
                1672585200.0,
                1672588800.0,
                1672592400.0,
                1672596000.0,
                1672599600.0,
                1672603200.0,
                1672606800.0,
                1672610400.0,
                1672614000.0,
            ), hourlyValues["time"])
            assertContains(hourlyValues, AirQuality.Hourly.pm25)
            assertContentEquals(arrayOf(
                8.8,
                8.7,
                8.0,
                7.0,
                6.0,
                4.4,
                4.2,
                4.5,
                4.6,
                4.6,
                4.7,
                4.5,
                4.7,
                4.6,
                4.9,
                5.1,
                6.6,
                7.9,
                7.5,
                7.4,
                8.0,
                7.8,
                9.1,
                9.9
            ), hourlyValues[AirQuality.Hourly.pm25])
            assertContains(hourlyValues, AirQuality.Hourly.usAqi)
            assertContentEquals(arrayOf(
                17.0,
                18.0,
                19.0,
                19.0,
                20.0,
                21.0,
                21.0,
                21.0,
                22.0,
                22.0,
                23.0,
                23.0,
                23.0,
                24.0,
                24.0,
                24.0,
                25.0,
                25.0,
                25.0,
                25.0,
                26.0,
                26.0,
                26.0,
                26.0
            ), hourlyValues[AirQuality.Hourly.usAqi])
        }
    }

}
