package com.openmeteo.apix.common.response

import kotlinx.serialization.SerialName

interface ResponseGenerationTimed : Response {
    @SerialName("generationtime_ms")
    val generationTimeMs: Double
}
