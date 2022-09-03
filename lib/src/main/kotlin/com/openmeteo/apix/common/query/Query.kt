package com.openmeteo.apix.common.query

import kotlinx.serialization.*
import java.net.URL
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

interface Query {

    companion object {

        internal fun key(property: KProperty1<Query, *>) =
            property.findAnnotation<SerialName>()
                ?.value ?: property.name

        @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
        internal fun value(any: Any?) : String =
            when (any) {
                is Iterable<*> -> any
                    .joinToString(",") { value(it) }
                is Enum<*> -> any
                    .javaClass.kotlin.serializer().descriptor
                    .let { it.getElementName(it.getElementIndex(any.name)) }
                else -> any.toString()
            }

    }

    private fun value(property: KProperty1<Query, *>) =
        property.get(this)?.let { Companion.value(it) }

    private val nonTransientDeclaredMemberProperties get() =
        javaClass.kotlin.declaredMemberProperties
            .filter { !it.hasAnnotation<Transient>() }

    /**
     * Returns the object properties as a list of key-value pairs.
     *
     * Please note that pairs with null values are filtered out.
     */
    fun toList () =
        nonTransientDeclaredMemberProperties
            .mapNotNull { value(it)?.let { v -> key(it) to v } }

    fun toMap() =
        toList().toMap()

    fun asString() =
        toList().joinToString("&", "?") { (k, v) -> "$k=$v" }

    fun toURL(context: URL) =
        URL(context, "${context.path}${asString()}")

    /**
     * Please note that the resulting object won't inherit the actual properties,
     * instead it will override the [toList()](Query.toList()) method, which is
     * used by all other methods, hence avoiding overrides.
     */
    operator fun plus(other: Query) =
        toList().let {
            object : Query {
                override fun toList(): List<Pair<String, String>> =
                    it + other.toList()
            }
        }

}
