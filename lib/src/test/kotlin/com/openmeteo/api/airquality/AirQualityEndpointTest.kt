package com.openmeteo.api.airquality

import com.openmeteo.api.airquality.params.Hourly
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.*

class AirQualityEndpointTest {
    companion object {
        val endpoint = AirQualityEndpoint()
    }

    @Test
    @ExperimentalSerializationApi
    fun `pm10, carbonMonoxide, nitrogenDioxide (scoped)`() {
        val response = endpoint(
            hourly = with(Hourly.Scope) {listOf(
                pm10, carbonMonoxide, nitrogenDioxide
            )}
        ).getOrThrow()
        val hourly = response.hourly!!
        with (hourly) {
            assertNotNull(pm10)
            assertNull(pm2_5)
            assertNotNull(carbonMonoxide)
            assertNotNull(nitrogenDioxide)
            assertNull(sulphurDioxide)
            assertNull(ozone)
            assertNull(aerosolOpticalDepth)
            assertNull(dust)
            assertNull(uvIndex)
            assertNull(uvIndexClearSky)
            assertNull(alderPollen)
            assertNull(birchPollen)
            assertNull(grassPollen)
            assertNull(mugwortPollen)
            assertNull(olivePollen)
            assertNull(ragweedPollen)
        }
    }
}
