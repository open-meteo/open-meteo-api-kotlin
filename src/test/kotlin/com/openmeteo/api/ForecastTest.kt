package com.openmeteo.api

import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.Units
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ForecastTest {

    @Test
    fun `Amsterdam, with past day`() {
        val query = Forecast.Query(
            latitude = 52.3738f,
            longitude = 4.8910f,
            daily = Forecast.Daily { listOf(
                weathercode, sunrise, sunset, temperature2mMax, temperature2mMin
            ) },
            timezone = Timezone.auto,
            pastDays = 1,
        )
        Forecast(query).getOrThrow().run {
            assertEquals(52.38f, latitude)
            assertEquals(4.9f, longitude)
            // assertEquals(utcOffsetSeconds, 7200) // TODO: changes based on summer time?
            assertEquals("Europe/Amsterdam", timezone.id)
            assertEquals("CEST", timezoneAbbreviation)
            assertEquals(17f, elevation)
            assert(dailyUnits.equals(mapOf(
                "time" to Units.UnixTime,
                "weathercode" to Units.WeatherCode,
                "sunrise" to Units.UnixTime,
                "sunset" to Units.UnixTime,
                "temperature_2m_max" to Units.Celsius,
                "temperature_2m_min" to Units.Celsius,
            )))
            assertContains(dailyValues, "time")
            assertContains(dailyValues, "weathercode")
            assertContains(dailyValues, "sunrise")
            assertContains(dailyValues, "sunset")
            assertContains(dailyValues, "temperature_2m_max")
            assertContains(dailyValues, "temperature_2m_min")
        }
    }

}
