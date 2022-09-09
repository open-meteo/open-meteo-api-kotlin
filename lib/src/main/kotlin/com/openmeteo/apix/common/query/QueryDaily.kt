package com.openmeteo.apix.common.query

interface QueryDaily : Query {
    val daily: Iterable<Options>?

    interface Options
}
