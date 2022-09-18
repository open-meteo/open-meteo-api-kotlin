package com.openmeteo.api.common.query

interface QueryDaily : QueryTimeFormat {
    val daily: Iterable<Options>?

    interface Options : Query.Options
}
