package com.openmeteo.api

import com.openmeteo.api.common.query.City
import com.openmeteo.api.common.time.Date
import com.openmeteo.api.common.time.TimeZone
import com.openmeteo.api.forecast.ForecastHourly
import com.openmeteo.api.marine.MarineDaily
import kotlin.test.Test
import kotlin.test.assertContentEquals

class OpenMeteoTest {

    @Test
    fun mixed() {

        val openMeteo = OpenMeteo(City.Amsterdam)

        val response = openMeteo(
            ForecastHourly.Weathercode,
            MarineDaily.WaveHeightMax,
            timeZone = TimeZone.auto,
            startDate = Date("2022-09-17"),
            endDate = Date("2022-09-17"),
        ).getOrThrow()

        assertContentEquals(
            arrayOf(
                1663365600.0,
                1663369200.0,
                1663372800.0,
                1663376400.0,
                1663380000.0,
                1663383600.0,
                1663387200.0,
                1663390800.0,
                1663394400.0,
                1663398000.0,
                1663401600.0,
                1663405200.0,
                1663408800.0,
                1663412400.0,
                1663416000.0,
                1663419600.0,
                1663423200.0,
                1663426800.0,
                1663430400.0,
                1663434000.0,
                1663437600.0,
                1663441200.0,
                1663444800.0,
                1663448400.0,
            ),
            response.hourlyValues[ForecastHourly.Time],
        )
        assertContentEquals(
            arrayOf(
                80.0,
                80.0,
                80.0,
                80.0,
                80.0,
                3.0,
                3.0,
                3.0,
                3.0,
                1.0,
                80.0,
                3.0,
                80.0,
                2.0,
                2.0,
                80.0,
                61.0,
                80.0,
                80.0,
                80.0,
                80.0,
                3.0,
                80.0,
                3.0,
            ),
            response.hourlyValues[ForecastHourly.Weathercode],
        )

        assertContentEquals(
            arrayOf(1663365600.0),
            response.dailyValues[MarineDaily.Time],
        )
        assertContentEquals(
            arrayOf(1.54),
            response.dailyValues[MarineDaily.WaveHeightMax],
        )


    }

}
