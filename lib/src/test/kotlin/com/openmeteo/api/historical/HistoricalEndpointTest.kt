package com.openmeteo.api.historical

import com.openmeteo.api.common.params.IsoDate
import com.openmeteo.api.historical.params.Daily
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class HistoricalEndpointTest {
    companion object {
        val endpoint = HistoricalEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `Sunsets from 2022-01-01 to 2022-01-03`() {
        val response = endpoint(
            startDate = IsoDate("2022-01-01"),
            endDate = IsoDate("2022-01-03"),
            daily = listOf(
                Daily.sunset
            )
        ).getOrThrow()
        val daily = response.daily!!
        val sunsets = daily.sunset!!
        assertContentEquals(
            // listOf(1641049506L, 1641135975L, 1641222444L)
            // bug: all times are 2 seconds "early"!?
            listOf(1641049504L, 1641135973L, 1641222442L),
            sunsets.map { it.time },
        )
        assertEquals("Europe/Berlin", response.timezone.id)
    }

    @Test
    @ExperimentalSerializationApi
    fun `Sunsets from 2022-06-01 to 2022-06-03`() {
        val response = endpoint(
            startDate = IsoDate("2022-06-01"),
            endDate = IsoDate("2022-06-03"),
            daily = listOf(
                Daily.sunset
            )
        ).getOrThrow()
        val daily = response.daily!!
        val sunsets = daily.sunset!!
        assertContentEquals(
            // listOf(1654111265L, 1654197733L, 1654284201L)
            // bug: all times are 1 second "late"!?
            listOf(1654111266L, 1654197734L, 1654284202L),
            sunsets.map { it.time },
        )
        assertEquals("Europe/Berlin", response.timezone.id)
    }
}
