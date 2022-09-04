package com.openmeteo.apix.common.query

interface QueryHourly {
    val hourly: Iterable<Options>
    interface Options
}
