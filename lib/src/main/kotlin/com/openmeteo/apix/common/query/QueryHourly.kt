package com.openmeteo.apix.common.query

interface QueryHourly : QueryTimeZone {
    val hourly: Iterable<Options>?

    interface Options
}
