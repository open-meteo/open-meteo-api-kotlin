package com.openmeteo.apix.common.query

import com.openmeteo.apix.common.time.Date
import kotlinx.serialization.SerialName

interface QueryDateRange : Query {
    @SerialName("start_date")
    val startDate: Date?

    @SerialName("end_date")
    val endDate: Date?
}
