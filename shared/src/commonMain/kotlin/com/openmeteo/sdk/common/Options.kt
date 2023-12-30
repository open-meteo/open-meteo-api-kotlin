package com.openmeteo.sdk.common

import com.openmeteo.sdk.common.Options.Daily
import com.openmeteo.sdk.common.Options.Hourly
import com.openmeteo.sdk.common.Options.Models

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

    companion object {
        /**
         * Join a list of options to a string (comma separated)
         */
        inline operator fun <T : Options> invoke(
            scope: T,
            vararg extra: Any,
            block: T.() -> List<String>
        ) = (scope.block() + extra).joinToString(",")

    }

    abstract class Listable<T : Listable<T>> : Options {
        /**
         * Join a list of options to a string (comma separated)
         */
        @Suppress("UNCHECKED_CAST")
        inline operator fun invoke(
            vararg extra: Any,
            block: T.() -> List<String>
        ) = (((this as? T)?.block() ?: listOf()) + extra)
            .joinToString(",")
    }
}
