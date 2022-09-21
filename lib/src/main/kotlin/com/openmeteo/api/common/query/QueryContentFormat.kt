package com.openmeteo.api.common.query

import com.openmeteo.api.common.http.ContentFormat

interface QueryContentFormat : Query {
    val format: ContentFormat? get() = null
}
