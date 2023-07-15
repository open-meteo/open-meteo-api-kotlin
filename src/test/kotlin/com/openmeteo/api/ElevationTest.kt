package com.openmeteo.api

import kotlin.test.Test
import kotlin.test.assertContentEquals

class ElevationTest {

    @Test
    fun `Two coordinates (pairs of float-float)`() {
        val query = Elevation.Query(
            0f to 0f,
            52.52f to 13.41f,
        )
        Elevation(query).getOrThrow().run {
            assertContentEquals(
                floatArrayOf(
                    0f, 38f
                ), elevation
            )
        }
    }

    @Test
    fun `Two coordinates (lists of floats`() {
        val query = Elevation.Query(
            latitudes = listOf(0f, 52.52f),
            longitudes = listOf(0f, 13.41f),
        )
        Elevation(query).getOrThrow().run {
            assertContentEquals(
                floatArrayOf(
                    0f, 38f
                ), elevation
            )
        }
    }

}
