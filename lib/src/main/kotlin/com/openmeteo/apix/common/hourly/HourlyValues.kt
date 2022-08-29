package com.openmeteo.apix.common.hourly

import com.openmeteo.apix.common.time.Time

interface HourlyValues {
    val time: Array<Time>
}
