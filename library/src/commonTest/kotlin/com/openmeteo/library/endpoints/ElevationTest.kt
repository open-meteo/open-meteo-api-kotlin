package com.openmeteo.library.endpoints

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ElevationTest {

    @Test
    fun two_locations() = runTest {
        val elevation = Elevation(listOf(45.6495f,45.5946f), listOf(13.7768f,10.7115f))
        assertEquals(elevation(), listOf(Elevation.Response(
            listOf(18f,313f)
        )))
    }
}
