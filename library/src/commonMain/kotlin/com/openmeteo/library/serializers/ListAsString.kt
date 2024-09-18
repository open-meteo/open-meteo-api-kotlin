package com.openmeteo.library.serializers

import kotlinx.serialization.Serializable

/**
 * A list that gets encoded as a single String value, instead of a nested element
 */
public typealias ListAsString<T> =
        @Serializable(with = ListAsStringSerializer::class) List<T>