package com.openmeteo.sdk

import com.openmeteo.sdk.common.Query
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class QueryTest {

    @Serializable
    class MyQuery(
        override val startDate: LocalDate?,
        override val endDate: LocalDate?,
    ) : Query(), Query.DateRange

    @Test
    fun toStringTest() {
        val myQuery = MyQuery(
            LocalDate.parse("2024-01-01"),
            LocalDate.parse("2024-01-02")
        )
        assertEquals(Query.encodeToString(myQuery), "?startDate=2024-01-01&endDate=2024-01-02")
    }

    @Test
    fun nullValueToStringTest() {
        val myQuery = MyQuery(LocalDate.parse("2024-01-01"), null)
        assertEquals(Query.encodeToString(myQuery), "?startDate=2024-01-01")
    }

}
