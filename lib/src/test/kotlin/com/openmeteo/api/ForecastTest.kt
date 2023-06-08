package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Timezone
import com.openmeteo.api.common.units.Unit
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ForecastTest {

    @Test
    fun `Amsterdam, with past day`() {
        val query = Forecast.Query(
            latitude = City.Amsterdam.latitude,
            longitude = City.Amsterdam.longitude,
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
                    "time" to Unit.UnixTime,
                    "weathercode" to Unit.WeatherCode,
                    "sunrise" to Unit.UnixTime,
                    "sunset" to Unit.UnixTime,
                    "temperature_2m_max" to Unit.Celsius,
                    "temperature_2m_min" to Unit.Celsius,
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
