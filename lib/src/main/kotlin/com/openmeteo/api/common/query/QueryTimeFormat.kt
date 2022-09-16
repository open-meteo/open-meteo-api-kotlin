package com.openmeteo.api.common.query

import com.openmeteo.api.common.time.TimeFormat
import kotlinx.serialization.SerialName

interface QueryTimeFormat : Query {
    @SerialName("timeformat")
    val timeFormat: TimeFormat get()
            = TimeFormat.UnixTime
}
