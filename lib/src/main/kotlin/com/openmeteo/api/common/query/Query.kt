package com.openmeteo.api.common.query

import kotlinx.serialization.*
import java.net.URL
import java.net.URLEncoder
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses

/**
 * An HTTP query
 */
interface Query {

    companion object {

        /**
         * Try to find the SerialName of a key, looping over superclasses
         */
        internal fun key(property: KProperty1<Query, *>, kClass: KClass<*>) =
            // if the property has a custom SerialName get it
            property.findAnnotation<SerialName>()?.value
                // else, loop over all superclasses, until a name is found
                ?: kClass.superclasses.firstNotNullOfOrNull { superClass ->
                    superClass.memberProperties
                        // if a superclass has the property we're looking for
                        .firstOrNull { it.name == property.name }
                        // if the property has a custom SerialName get it
                        ?.findAnnotation<SerialName>()?.value
                } ?: property.name // default to its name

        /**
         * Get the key name from a reified type
         */
        internal inline fun <reified T> key(property: KProperty1<Query, *>) =
            key(property, T::class)

        /**
         * Stringify a value (arrays are ',' joined, enums are looked up for
         * [SerialName]s; nulls are only filtered out from arrays)
         */
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

    /**
     * Useful to convert, for example, spaces to "%20"
     */
    private fun urlEncode(text: String) =
        URLEncoder.encode(text, "utf-8")

    /**
     * Calls the value companion method with `this` values
     */
    private fun value(property: KProperty1<Query, *>) =
        Companion.value(property.get(this))

    /**
     * Get all non-Transient properties
     */
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

    /**
     * Like `.toList().toMap()`
     */
    fun toMap() =
        toList().toMap()

    /**
     * URL encode the query as `?key0=value0&key1=value1&...keyN=valueN` format
     */
    fun asString() =
        urlEncode(toList().joinToString("&", "?") { (k, v) -> "$k=$v" })

    /**
     * Replace the context [URL] query with this one
     */
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
