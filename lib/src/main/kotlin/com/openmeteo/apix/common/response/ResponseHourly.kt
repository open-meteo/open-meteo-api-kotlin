package com.openmeteo.apix.common.response

import kotlinx.serialization.SerialName

interface ResponseHourly : Response {
    @SerialName("hourly_units")
    val hourlyUnits: Map<String, String>
    @SerialName("hourly")
    val hourlyValues: Map<String, Array<*>>
}
