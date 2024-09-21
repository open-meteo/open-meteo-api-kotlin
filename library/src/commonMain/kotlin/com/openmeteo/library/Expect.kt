package com.openmeteo.library

import io.ktor.client.statement.HttpResponse

/**
 * Something that expects a [format] encoded [R] response, that can be decoded with [decode]
 */
internal interface Expect<R> {
    public val format: String
    public suspend fun decode(response: HttpResponse): List<R>
}

// useful for json responses, where:
// - one location -> R
// - more locations -> array of R
// flatbuffers format always returns array of R
internal val <R> Expect<R>.expectedElements: Int
    get() = if (this is Query.Coordinates.Multiple) this.latitude.size else 1
