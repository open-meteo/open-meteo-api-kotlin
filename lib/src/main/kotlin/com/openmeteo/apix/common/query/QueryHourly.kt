package com.openmeteo.apix.common.query

interface QueryHourly : QueryTimeZone, QueryTimeFormat {
    val hourly: Iterable<Options>?

    interface Options
}
