package com.openmeteo.api

import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.LengthUnit
import com.openmeteo.api.common.units.Units
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MarineTest {

    @Test
    fun `Amsterdam, 1st January 2023, imperial length units`() {
        val query = Marine.Query(
            latitude = 52.3738f,
            longitude = 4.8910f,
            hourly = Marine.Hourly { listOf(
                waveHeight, waveDirection, wavePeriod,
                windWaveHeight, windWaveDirection, windWavePeriod, windWavePeakPeriod,
                swellWaveHeight, swellWaveDirection, swellWavePeriod, swellWavePeakPeriod,
            ) },
            daily = Marine.Daily { listOf(
                waveHeightMax, waveDirectionDominant, wavePeriodMax,
                windWaveHeightMax, windWaveDirectionDominant, windWavePeriodMax, windWavePeakPeriodMax,
                swellWaveHeightMax, swellWaveDirectionDominant, swellWavePeriodMax, swellWavePeakPeriodMax,
            ) },
            startDate = Date("2023-01-01"),
            endDate = Date("2023-01-01"),
            timezone = Timezone.getTimeZone("Europe/Berlin"),
            lengthUnit = LengthUnit.Imperial,
        )
        Marine(query).getOrThrow().run {
            assertEquals(52f, latitude)
            assertEquals(4.75f, longitude)
            // assertEquals(utcOffsetSeconds, 7200)
            assertEquals("Europe/Berlin", timezone.id)
            assertEquals("CEST", timezoneAbbreviation)
            with(Marine.Hourly) {
                assertEquals(
                    hourlyUnits, mapOf(
                        "time" to Units.UnixTime,
                        waveHeight to Units.Feet,
                        waveDirection to Units.DecimalDegrees,
                        wavePeriod to Units.Seconds,
                        windWaveHeight to Units.Feet,
                        windWaveDirection to Units.DecimalDegrees,
                        windWavePeriod to Units.Seconds,
                        windWavePeakPeriod to Units.Seconds,
                        swellWaveHeight to Units.Feet,
                        swellWaveDirection to Units.DecimalDegrees,
                        swellWavePeriod to Units.Seconds,
                        swellWavePeakPeriod to Units.Seconds,
                    )
                )
                assertContains(hourlyValues, "time")
                assertContentEquals(
                    arrayOf(
                        1672524000.0,
                        1672527600.0,
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
                        1672606800.0
                    ), hourlyValues["time"]
                )
                assertContains(hourlyValues, waveHeight)
                assertContentEquals(
                    arrayOf(
                        2.231,
                        2.231,
                        2.231,
                        2.231,
                        2.165,
                        2.165,
                        2.1,
                        1.969,
                        1.903,
                        1.837,
                        1.772,
                        1.706,
                        1.64,
                        1.509,
                        1.444,
                        1.312,
                        1.115,
                        0.984,
                        0.919,
                        0.853,
                        0.787,
                        0.787,
                        0.787,
                        0.787,
                    ), hourlyValues[waveHeight]
                )
                assertContains(hourlyValues, waveDirection)
                assertContentEquals(
                    arrayOf(
                        223.0,
                        226.0,
                        229.0,
                        230.0,
                        232.0,
                        233.0,
                        235.0,
                        237.0,
                        239.0,
                        240.0,
                        242.0,
                        243.0,
                        240.0,
                        236.0,
                        233.0,
                        231.0,
                        230.0,
                        228.0,
                        224.0,
                        219.0,
                        215.0,
                        206.0,
                        198.0,
                        189.0,
                    ), hourlyValues[waveDirection]
                )
                assertContains(hourlyValues, wavePeriod)
                assertContentEquals(
                    arrayOf(
                        3.2,
                        3.3,
                        3.4,
                        3.5,
                        3.65,
                        3.75,
                        3.85,
                        4.0,
                        4.05,
                        4.05,
                        4.0,
                        3.9,
                        3.65,
                        3.35,
                        3.15,
                        3.1,
                        3.15,
                        3.15,
                        3.1,
                        3.1,
                        3.0,
                        2.85,
                        2.7,
                        2.55,
                    ), hourlyValues[wavePeriod]
                )
                assertContains(hourlyValues, windWaveHeight)
                assertContentEquals(
                    arrayOf(
                        2.231,
                        2.165,
                        2.165,
                        2.1,
                        2.034,
                        1.969,
                        1.837,
                        1.772,
                        1.64,
                        1.575,
                        1.509,
                        1.444,
                        1.378,
                        1.378,
                        1.312,
                        1.115,
                        0.984,
                        0.787,
                        0.722,
                        0.656,
                        0.591,
                        0.591,
                        0.656,
                        0.656,
                    ), hourlyValues[windWaveHeight]
                )
                assertContains(hourlyValues, windWaveDirection)
                assertContentEquals(
                    arrayOf(
                        221.0,
                        223.0,
                        226.0,
                        226.0,
                        226.0,
                        226.0,
                        227.0,
                        228.0,
                        229.0,
                        230.0,
                        231.0,
                        232.0,
                        230.0,
                        227.0,
                        225.0,
                        222.0,
                        219.0,
                        216.0,
                        208.0,
                        199.0,
                        191.0,
                        183.0,
                        176.0,
                        168.0,
                    ), hourlyValues[windWaveDirection]
                )
                assertContains(hourlyValues, windWavePeriod)
                assertContentEquals(
                    arrayOf(
                        3.0,
                        3.05,
                        3.1,
                        3.1,
                        3.1,
                        3.1,
                        3.05,
                        2.95,
                        2.85,
                        2.75,
                        2.65,
                        2.55,
                        2.5,
                        2.4,
                        2.35,
                        2.25,
                        2.15,
                        2.05,
                        1.95,
                        1.8,
                        1.7,
                        1.65,
                        1.65,
                        1.65
                    ), hourlyValues[windWavePeriod]
                )
                assertContains(hourlyValues, windWavePeakPeriod)
                assertContentEquals(
                    arrayOf(
                        2.6,
                        2.5,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.45,
                        2.4,
                        2.3,
                        2.2,
                        2.1,
                        1.95,
                        1.85,
                        1.8,
                        1.85,
                        1.85
                    ), hourlyValues[windWavePeakPeriod]
                )
                assertContains(hourlyValues, swellWaveHeight)
                assertContentEquals(
                    arrayOf(
                        0.525,
                        0.525,
                        0.525,
                        0.656,
                        0.722,
                        0.853,
                        0.919,
                        0.984,
                        1.05,
                        1.05,
                        0.984,
                        0.984,
                        0.853,
                        0.787,
                        0.656,
                        0.591,
                        0.591,
                        0.525,
                        0.525,
                        0.459,
                        0.459,
                        0.459,
                        0.459,
                        0.459,
                    ), hourlyValues[swellWaveHeight]
                )
                assertContains(hourlyValues, swellWaveDirection)
                assertContentEquals(
                    arrayOf(
                        280.0,
                        280.0,
                        280.0,
                        278.0,
                        277.0,
                        275.0,
                        271.0,
                        268.0,
                        264.0,
                        265.0,
                        266.0,
                        267.0,
                        267.0,
                        268.0,
                        268.0,
                        267.0,
                        266.0,
                        265.0,
                        264.0,
                        262.0,
                        261.0,
                        257.0,
                        254.0,
                        250.0,
                    ), hourlyValues[swellWaveDirection]
                )
                assertContains(hourlyValues, swellWavePeriod)
                assertContentEquals(
                    arrayOf(
                        7.9,
                        7.9,
                        7.85,
                        7.75,
                        7.6,
                        7.45,
                        7.3,
                        7.15,
                        7.05,
                        7.0,
                        6.95,
                        6.9,
                        6.85,
                        6.85,
                        6.75,
                        6.55,
                        6.35,
                        6.1,
                        5.85,
                        5.65,
                        5.4,
                        5.1,
                        4.75,
                        4.75,
                    ), hourlyValues[swellWavePeriod]
                )
                assertContains(hourlyValues, swellWavePeakPeriod)
                assertContentEquals(
                    arrayOf(
                        7.1,
                        7.4,
                        7.65,
                        7.75,
                        7.8,
                        7.65,
                        7.25,
                        6.7,
                        6.3,
                        6.2,
                        6.25,
                        6.3,
                        6.3,
                        6.3,
                        6.3,
                        6.3,
                        6.3,
                        6.3,
                        6.05,
                        5.75,
                        6.3,
                        8.45,
                        11.35,
                        13.5,
                    ), hourlyValues[swellWavePeakPeriod]
                )
            }
            with(Marine.Daily) {
                assertEquals(
                    dailyUnits, mapOf(
                        "time" to Units.UnixTime,
                        waveHeightMax to Units.Feet,
                        waveDirectionDominant to Units.DecimalDegrees,
                        wavePeriodMax to Units.Seconds,
                        windWaveHeightMax to Units.Feet,
                        windWaveDirectionDominant to Units.DecimalDegrees,
                        windWavePeriodMax to Units.Seconds,
                        windWavePeakPeriodMax to Units.Seconds,
                        swellWaveHeightMax to Units.Feet,
                        swellWaveDirectionDominant to Units.DecimalDegrees,
                        swellWavePeriodMax to Units.Seconds,
                        swellWavePeakPeriodMax to Units.Seconds,
                    )
                )
                assertContains(dailyValues, "time")
                assertContentEquals(arrayOf(1672524000.0), dailyValues["time"])
                assertContains(dailyValues, waveHeightMax)
                assertContentEquals(arrayOf(2.231), dailyValues[waveHeightMax])
                assertContains(dailyValues, waveDirectionDominant)
                assertContentEquals(arrayOf(227.0), dailyValues[waveDirectionDominant])
                assertContains(dailyValues, wavePeriodMax)
                assertContentEquals(arrayOf(4.05), dailyValues[wavePeriodMax])
                assertContains(dailyValues, windWaveHeightMax)
                assertContentEquals(arrayOf(2.231), dailyValues[windWaveHeightMax])
                assertContains(dailyValues, windWaveDirectionDominant)
                assertContentEquals(arrayOf(216.0), dailyValues[windWaveDirectionDominant])
                assertContains(dailyValues, windWavePeriodMax)
                assertContentEquals(arrayOf(3.1), dailyValues[windWavePeriodMax])
                assertContains(dailyValues, windWavePeakPeriodMax)
                assertContentEquals(arrayOf(2.6), dailyValues[windWavePeakPeriodMax])
                assertContains(dailyValues, swellWaveHeightMax)
                assertContentEquals(arrayOf(1.05,), dailyValues[swellWaveHeightMax])
                assertContains(dailyValues, swellWaveDirectionDominant)
                assertContentEquals(arrayOf(268.0), dailyValues[swellWaveDirectionDominant])
                assertContains(dailyValues, swellWavePeriodMax)
                assertContentEquals(arrayOf(7.9), dailyValues[swellWavePeriodMax])
                assertContains(dailyValues, swellWavePeakPeriodMax)
                assertContentEquals(arrayOf(13.5), dailyValues[swellWavePeakPeriodMax])
            }
        }
    }

}
