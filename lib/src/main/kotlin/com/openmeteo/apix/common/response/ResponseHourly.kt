package com.openmeteo.apix.common.response

import com.openmeteo.apix.common.time.Time
import com.openmeteo.apix.common.time.TimeFormat
import kotlinx.serialization.SerialName

interface ResponseHourly : ResponseTimeZone {
    @SerialName("hourly_units")
    val hourlyUnits: Units

    @SerialName("hourly")
    val hourlyValues: Values

    interface Units {
        val time: TimeFormat
    }

    interface Values {
        val time: Array<Time>
    }
}
