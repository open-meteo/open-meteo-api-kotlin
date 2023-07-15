package com.openmeteo.api

import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.units.Units
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class FloodTest {

    @Test
    fun `Amsterdam, 1st January 2023`() {
        val query = Flood.Query(
            latitude = 52.3738f,
            longitude = 4.8910f,
            daily = Flood.Daily {
                listOf(
                    riverDischarge,
                    riverDischargeMean, riverDischargeMedian,
                    riverDischargeMax, riverDischargeMin,
                    riverDischargeP25, riverDischargeP75
                )
            },
            startDate = Date("2023-01-01"),
            endDate = Date("2023-01-01"),
            models = Flood.Models {
                listOf(seamlessV3)
            }
        )
        Flood(query).getOrThrow().run {
            assertEquals(52.4f, latitude)
            assertEquals(4.900009f, longitude)
            assertEquals(utcOffsetSeconds, 0)
            assertEquals("GMT", timezone.id)
            assertEquals("GMT", timezoneAbbreviation)
            with(Flood.Daily) {
                assertEquals(
                    dailyUnits, mapOf(
                        "time" to Units.UnixTime,
                        riverDischarge to Units.CubeMetersPerSecond,
                        riverDischargeMean to Units.CubeMetersPerSecond,
                        riverDischargeMedian to Units.CubeMetersPerSecond,
                        riverDischargeMax to Units.CubeMetersPerSecond,
                        riverDischargeMin to Units.CubeMetersPerSecond,
                        riverDischargeP25 to Units.CubeMetersPerSecond,
                        riverDischargeP75 to Units.CubeMetersPerSecond,
                    )
                )
                assertContains(dailyValues, "time")
                assertContentEquals(arrayOf(1672531200.0), dailyValues["time"])
                assertContains(dailyValues, riverDischarge)
                assertContentEquals(arrayOf(4.69), dailyValues[riverDischarge])
                assertContains(dailyValues, riverDischargeMean)
                assertContentEquals(arrayOf(4.57), dailyValues[riverDischargeMean])
                assertContains(dailyValues, riverDischargeMedian)
                assertContentEquals(arrayOf(4.59), dailyValues[riverDischargeMedian])
                assertContains(dailyValues, riverDischargeMax)
                assertContentEquals(arrayOf(4.92), dailyValues[riverDischargeMax])
                assertContains(dailyValues, riverDischargeMin)
                assertContentEquals(arrayOf(4.25), dailyValues[riverDischargeMin])
                assertContains(dailyValues, riverDischargeP25)
                assertContentEquals(arrayOf(4.43), dailyValues[riverDischargeP25])
                assertContains(dailyValues, riverDischargeP75)
                assertContentEquals(arrayOf(4.71), dailyValues[riverDischargeP75])
            }
        }
    }

}
