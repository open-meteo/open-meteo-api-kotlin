package com.openmeteo.api.common.query

interface QueryHourly : QueryTimeZone, QueryTimeFormat {
    val hourly: Iterable<Options>?

    interface Options : Query.Options
}
