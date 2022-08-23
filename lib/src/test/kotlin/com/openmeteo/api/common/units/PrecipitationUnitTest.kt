package com.openmeteo.api.common.units

import kotlin.test.Test
import kotlin.test.assertEquals

class PrecipitationUnitTest {

    @Test
    fun `cm param is converted to mm`() {
        assertEquals(PrecipitationUnit.mm, PrecipitationUnit.cm.param())
    }
}
