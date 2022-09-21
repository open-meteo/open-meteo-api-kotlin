package com.openmeteo.api.common.query

import kotlinx.serialization.*
import java.net.URL
import java.net.URLEncoder
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.*

interface Query {

    companion object {

        internal fun key(property: KProperty1<Query, *>, kClass: KClass<*>) =
            property.findAnnotation<SerialName>()?.value
                ?: kClass.superclasses.firstNotNullOfOrNull { superClass ->
                    superClass.memberProperties
                        .firstOrNull { it.name == property.name }
                        ?.findAnnotation<SerialName>()?.value
                } ?: property.name

        internal inline fun <reified T> key(property: KProperty1<Query, *>) =
            key(property, T::class)

        @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
        internal fun value(any: Any?): String? =
            when (any) {
                is Iterable<*> -> any
                    .mapNotNull { value(it) }
                    .takeUnless { it.isEmpty() }
                    ?.joinToString(",")
                is Enum<*> -> runCatching {
                    any.javaClass.kotlin.serializer()
                        .descriptor.getElementName(any.ordinal)
                }.getOrNull()
                null -> null
                else -> any.toString()
            }

    }

    private fun urlEncode(text: String) =
        URLEncoder.encode(text, "utf-8")

    private fun value(property: KProperty1<Query, *>) =
        Companion.value(property.get(this))

    private val memberProperties
        get() =
            javaClass.kotlin.memberProperties
                .filter { !it.hasAnnotation<Transient>() }

    /**
     * Returns the object properties as a list of key-value pairs.
     *
     * Please note that pairs with null values are filtered out.
     */
    fun toList() =
        memberProperties
            .mapNotNull { value(it)?.let { v -> key(it, javaClass.kotlin) to v } }

    fun toMap() =
        toList().toMap()

    fun asString() =
        urlEncode(toList().joinToString("&", "?") { (k, v) -> "$k=$v" })

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

    interface Options
}
