package com.openmeteo.apix.common.response

import com.openmeteo.apix.common.time.Time
import com.openmeteo.apix.common.time.TimeFormat
import kotlinx.serialization.SerialName

interface ResponseDaily : Response {
    @SerialName("daily_units")
    val dailyUnits: Units?

    @SerialName("daily")
    val dailyValues: Values?

    interface Units {
        val time: TimeFormat
    }

    interface Values {
        val time: Array<Time>
    }
}
