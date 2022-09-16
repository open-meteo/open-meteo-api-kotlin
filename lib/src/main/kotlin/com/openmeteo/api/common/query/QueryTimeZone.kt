package com.openmeteo.api.common.query

import com.openmeteo.api.common.time.TimeZone
import kotlinx.serialization.SerialName

interface QueryTimeZone : Query {
    @SerialName("timezone")
    val timeZone: TimeZone?
}
