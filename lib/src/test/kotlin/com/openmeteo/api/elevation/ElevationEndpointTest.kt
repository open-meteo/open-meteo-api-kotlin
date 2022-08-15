package com.openmeteo.api.elevation

import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.*

class ElevationEndpointTest {
    companion object {
        val elevationEndpoint = ElevationEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun Berlin() {
        val response = elevationEndpoint(
            52.5235f to 13.4115f,
        ).getOrThrow()
        assertContentEquals(floatArrayOf(38f), response.elevation)
    }

    @Test
    @ExperimentalSerializationApi
    fun `Berlin, Tokyo`() {
        val response = elevationEndpoint(
            52.5235f to 13.4115f,
            35.6785f to 139.6823f,
        ).getOrThrow()
        assertContentEquals(floatArrayOf(38f, 48f), response.elevation)
    }
}
