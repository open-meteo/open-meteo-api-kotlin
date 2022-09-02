package com.openmeteo.apix.common.http

import kotlinx.serialization.*
import java.net.URL
import kotlin.reflect.full.declaredMemberProperties

interface Query {
    companion object {

        @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
        internal fun value(any: Any?): String = when (any) {
            is Iterable<*> -> any.joinToString(",") { value(it) }
            is Enum<*> -> any.javaClass.kotlin.serializer()
                .descriptor.getElementName(any.ordinal)
            else -> any.toString() // null is not skipped! ok
        }

        internal fun prefix(prefix: String?) =
            prefix?.takeUnless { it.isEmpty() }
                ?.let { "$it&" } ?: ""
    }

    private val properties get() = javaClass
        .kotlin.declaredMemberProperties
        .map { it.name to it.get(this) }

    private fun asString(prefix: String? = null) = properties
        .mapNotNull { (k, v) -> v?.let { "$k=${value(v)}" } }
        .joinToString("&", "?${prefix(prefix)}")


    fun apply(on: URL) =
        URL(on, "${on.path}${asString(on.query)}")
}
