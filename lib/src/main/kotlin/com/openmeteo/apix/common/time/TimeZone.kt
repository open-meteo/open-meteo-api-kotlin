package com.openmeteo.apix.common.time

import kotlinx.serialization.Serializable
import java.time.ZoneId

/**
 * A [TimeZone] class wrapper
 *
 * Please note that null is used as an internal value to represent the `"auto"` option
 */
@Serializable
@JvmInline
value class TimeZone private constructor(
    val id: String = "auto"
) {
    constructor(
        timeZone: java.util.TimeZone? = null
    ) : this(
        timeZone?.id ?: "auto"
    )

    companion object {
        val auto = TimeZone("auto")
        fun getTimeZone(id: String) =
            TimeZone(java.util.TimeZone.getTimeZone(id))

        fun getTimeZone(id: ZoneId) =
            TimeZone(java.util.TimeZone.getTimeZone(id))
    }

    val timeZone: java.util.TimeZone?
        get() = java.util.TimeZone.getTimeZone(id)

    override fun toString(): String = id
}
