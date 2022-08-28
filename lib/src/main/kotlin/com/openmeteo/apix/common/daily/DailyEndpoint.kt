package com.openmeteo.apix.common.daily

import com.openmeteo.apix.common.http.Endpoint
import com.openmeteo.apix.common.time.TimeZone

interface DailyEndpoint : Endpoint {
    val daily: Iterable<DailyParams>?
    val timeZone: TimeZone?
}
