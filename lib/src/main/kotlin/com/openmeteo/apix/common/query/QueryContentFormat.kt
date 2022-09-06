package com.openmeteo.apix.common.query

import com.openmeteo.apix.common.http.ContentFormat

interface QueryContentFormat : Query {
    val format: ContentFormat
}
