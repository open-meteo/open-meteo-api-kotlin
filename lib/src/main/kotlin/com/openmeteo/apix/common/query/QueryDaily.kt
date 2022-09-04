package com.openmeteo.apix.common.query

interface QueryDaily {
    val daily: Iterable<Options>
    interface Options
}
