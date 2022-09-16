package com.openmeteo.api.common.response

import kotlinx.serialization.SerialName

interface ResponseGenerationTimed : Response {
    @SerialName("generationtime_ms")
    val generationTimeMs: Float
}
