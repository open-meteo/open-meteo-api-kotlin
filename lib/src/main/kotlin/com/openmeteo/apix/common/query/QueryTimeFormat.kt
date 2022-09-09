package com.openmeteo.apix.common.query

import com.openmeteo.apix.common.time.TimeFormat
import kotlinx.serialization.SerialName

interface QueryTimeFormat : Query {
    @SerialName("timeformat")
    val timeFormat: TimeFormat get()
            = TimeFormat.UnixTime
}
