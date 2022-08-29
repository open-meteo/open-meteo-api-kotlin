package com.openmeteo.apix.common.time

import kotlinx.serialization.SerialName

interface GenerationTimedResponse {
    @SerialName("generationtime_ms")
    val generationTime: Double
}
