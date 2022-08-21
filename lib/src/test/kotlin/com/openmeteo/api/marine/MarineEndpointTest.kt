package com.openmeteo.api.marine

import com.openmeteo.api.common.TimeZone
import com.openmeteo.api.common.params.IsoDate
import com.openmeteo.api.marine.params.Daily
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals

class MarineEndpointTest {
    companion object {
        val endpoint = MarineEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Empty query doesn't throw`() {
        endpoint().getOrThrow()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Max wave height of the upcoming days`() {
        val response = endpoint(
            daily = listOf(
                Daily.wave_height_max
            ), timeZone = TimeZone("Europe/Berlin")
        ).getOrThrow()
        val dailyUnits = response.daily_units!!
        val dailyWaveHeightMaxUnit = dailyUnits.wave_height_max!!
        val daily = response.daily!!
        val dailyTime = daily.time
        val dailyWaveHeightMax = daily.wave_height_max!!
        val dailyTimeZipWaveHeightMax = dailyTime.zip(dailyWaveHeightMax)
        for ((time, waveHeightMax) in dailyTimeZipWaveHeightMax)
            println("On ${IsoDate(time)} the max wave height is going to be $waveHeightMax $dailyWaveHeightMaxUnit")
    }

    @Test
    @ExperimentalSerializationApi
    fun `Daily auto timeZone`() {
        val response = endpoint(
            daily = listOf(
                Daily.wave_height_max
            ), timeZone = TimeZone("auto")
        ).getOrThrow()
        response.daily_units!!.wave_height_max!!
        response.daily!!.wave_height_max!!
        assertEquals("Europe/Berlin", response.timezone.id)
    }
}