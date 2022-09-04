package com.openmeteo.apix.common.response

import kotlinx.serialization.SerialName

interface ResponseDaily : Response {
    @SerialName("daily_units")
    val dailyUnits: Map<String, String>
    @SerialName("daily")
    val dailyValues: Map<String, Any>
}
