package com.openmeteo.api.common

import com.openmeteo.api.common.Options.Daily
import com.openmeteo.api.common.Options.Hourly
import com.openmeteo.api.common.Options.Models

/**
 * A parent type for [Models], [Daily], [Hourly] options.
 */
interface Options {

    /**
     * A generic type to identify [Models] options.
     */
    interface Models : Options

    /**
     * A generic type to identify [Daily] options.
     */
    interface Daily : Options

    /**
     * A generic type to identify [Hourly] options.
     */
    interface Hourly : Options

    /**
     * Shorthand for [listOf]
     */
    fun <T> of(vararg options: T) =
        listOf(*options)

    companion object {
        /**
         * Join a list of options to a string (comma separated)
         */
        fun <T : Options> list(
            scope: T,
            vararg extra: Any,
            block: T.() -> List<String>
        ) = (scope.run(block) + extra).joinToString(",")

    }

    abstract class Listable<T : Listable<T>> : Options {
        /**
         * Join a list of options to a string (comma separated)
         */
        @Suppress("UNCHECKED_CAST")
        inline fun list(
            vararg extra: Any,
            block: T.() -> List<String>
        ) = (((this as? T)?.block() ?: listOf()) + extra)
            .joinToString(",")
    }
}
