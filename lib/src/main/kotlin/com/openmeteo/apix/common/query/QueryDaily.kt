package com.openmeteo.apix.common.query

interface QueryDaily : QueryTimeFormat {
    val daily: Iterable<Options>?

    interface Options
}
