package com.openmeteo.apix.common.time

import com.openmeteo.apix.common.http.Endpoint

interface TimeZoneEndpoint : Endpoint {
    val timeZone: TimeZone?
}
