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
            hourly = listOf(
                Hourly.pm10, Hourly.carbon_monoxide, Hourly.nitrogen_dioxide
            )
        ).getOrThrow()
        val hourly = response.hourly!!
        with (hourly) {
            assertNotNull(pm10)
            assertNull(pm2_5)
            assertNotNull(carbon_monoxide)
            assertNotNull(nitrogen_dioxide)
            assertNull(sulphur_dioxide)
            assertNull(ozone)
            assertNull(aerosol_optical_depth)
            assertNull(dust)
            assertNull(uv_index)
            assertNull(uv_index_clear_sky)
            assertNull(alder_pollen)
            assertNull(birch_pollen)
            assertNull(grass_pollen)
            assertNull(mugwort_pollen)
            assertNull(olive_pollen)
            assertNull(ragweed_pollen)
        }
    }
}
