package com.openmeteo.apix.common.hourly

import com.openmeteo.apix.common.http.Endpoint

interface HourlyEndpoint : Endpoint {
    val hourly: Iterable<HourlyParams>?
}
