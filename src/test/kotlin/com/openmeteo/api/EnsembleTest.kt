package com.openmeteo.api

import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.units.Units
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class EnsembleTest {

    @Test
    fun `Amsterdam, 1st June 2023`() {
        val query = Ensemble.Query(
            latitude = 52.3738f,
            longitude = 4.8910f,
            hourly = Ensemble.Hourly { listOf(
                temperature2m,
            ) },
            models = Ensemble.Models { listOf(
                iconEu
            ) },
            startDate = Date("2023-06-01"),
            endDate = Date("2023-06-01"),
        )
        Ensemble(query).getOrThrow().run {
            assertEquals(52.375f, latitude)
            assertEquals(4.875f, longitude)
            assertEquals(utcOffsetSeconds, 0)
            assertEquals("GMT", timezone.id)
            assertEquals("GMT", timezoneAbbreviation)
            assertEquals(17f, elevation)
            assertEquals(mapOf(
                "time" to Units.UnixTime,
                "temperature_2m" to Units.Celsius,
                "temperature_2m_member01" to Units.Celsius,
                "temperature_2m_member02" to Units.Celsius,
                "temperature_2m_member03" to Units.Celsius,
                "temperature_2m_member04" to Units.Celsius,
                "temperature_2m_member05" to Units.Celsius,
                "temperature_2m_member06" to Units.Celsius,
                "temperature_2m_member07" to Units.Celsius,
                "temperature_2m_member08" to Units.Celsius,
                "temperature_2m_member09" to Units.Celsius,
                "temperature_2m_member10" to Units.Celsius,
                "temperature_2m_member11" to Units.Celsius,
                "temperature_2m_member12" to Units.Celsius,
                "temperature_2m_member13" to Units.Celsius,
                "temperature_2m_member14" to Units.Celsius,
                "temperature_2m_member15" to Units.Celsius,
                "temperature_2m_member16" to Units.Celsius,
                "temperature_2m_member17" to Units.Celsius,
                "temperature_2m_member18" to Units.Celsius,
                "temperature_2m_member19" to Units.Celsius,
                "temperature_2m_member20" to Units.Celsius,
                "temperature_2m_member21" to Units.Celsius,
                "temperature_2m_member22" to Units.Celsius,
                "temperature_2m_member23" to Units.Celsius,
                "temperature_2m_member24" to Units.Celsius,
                "temperature_2m_member25" to Units.Celsius,
                "temperature_2m_member26" to Units.Celsius,
                "temperature_2m_member27" to Units.Celsius,
                "temperature_2m_member28" to Units.Celsius,
                "temperature_2m_member29" to Units.Celsius,
                "temperature_2m_member30" to Units.Celsius,
                "temperature_2m_member31" to Units.Celsius,
                "temperature_2m_member32" to Units.Celsius,
                "temperature_2m_member33" to Units.Celsius,
                "temperature_2m_member34" to Units.Celsius,
                "temperature_2m_member35" to Units.Celsius,
                "temperature_2m_member36" to Units.Celsius,
                "temperature_2m_member37" to Units.Celsius,
                "temperature_2m_member38" to Units.Celsius,
                "temperature_2m_member39" to Units.Celsius,
            ), hourlyUnits)
            assertContentEquals(arrayOf(
                1685577600.0,
                1685581200.0,
                1685584800.0,
                1685588400.0,
                1685592000.0,
                1685595600.0,
                1685599200.0,
                1685602800.0,
                1685606400.0,
                1685610000.0,
                1685613600.0,
                1685617200.0,
                1685620800.0,
                1685624400.0,
                1685628000.0,
                1685631600.0,
                1685635200.0,
                1685638800.0,
                1685642400.0,
                1685646000.0,
                1685649600.0,
                1685653200.0,
                1685656800.0,
                1685660400.0
            ), hourlyValues["time"])
            assertContentEquals(arrayOf(
                9.8,
                9.3,
                9.5,
                9.5,
                9.4,
                9.6,
                9.6,
                10.0,
                11.2,
                12.9,
                13.5,
                14.3,
                13.8,
                13.2,
                13.1,
                13.3,
                13.2,
                12.7,
                11.8,
                11.1,
                10.5,
                9.9,
                9.9,
                9.7
            ), hourlyValues["temperature_2m"])
            assertContentEquals(arrayOf(
                10.6,
                10.3,
                10.1,
                10.0,
                10.2,
                10.6,
                10.7,
                10.8,
                11.7,
                12.9,
                14.2,
                15.3,
                14.8,
                15.3,
                14.7,
                14.4,
                14.0,
                13.5,
                12.9,
                11.8,
                11.2,
                10.9,
                10.6,
                10.3
            ), hourlyValues["temperature_2m_member01"])
            assertContentEquals(arrayOf(
                9.6,
                9.5,
                9.5,
                9.4,
                9.3,
                9.4,
                9.8,
                10.3,
                11.0,
                12.0,
                12.5,
                13.4,
                14.6,
                14.2,
                13.8,
                13.8,
                13.5,
                12.6,
                11.9,
                11.2,
                10.5,
                10.0,
                9.6,
                9.5
            ), hourlyValues["temperature_2m_member11"])

        }
    }

}
