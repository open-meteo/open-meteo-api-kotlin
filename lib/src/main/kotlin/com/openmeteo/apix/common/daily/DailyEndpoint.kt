package com.openmeteo.apix.common.daily

import com.openmeteo.apix.common.time.TimeZoneEndpoint

interface DailyEndpoint : TimeZoneEndpoint {
    val daily: Iterable<DailyParams>?
}
