package com.openmeteo.apix.common.query

import com.openmeteo.apix.common.time.TimeZone
import kotlinx.serialization.SerialName

interface QueryTimeZone : Query {
    @SerialName("timezone")
    val timeZone: TimeZone?
}
