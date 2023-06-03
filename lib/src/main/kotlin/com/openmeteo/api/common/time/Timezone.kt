package com.openmeteo.api.common.time

import kotlinx.serialization.Serializable
import java.time.ZoneId

/**
 * A [java.util.TimeZone] class wrapper.
 */
@Serializable
@JvmInline
value class Timezone private constructor(
    val id: String = "auto"
) {

    /**
     * `null` is translated to the `"auto"` option.
     */
    constructor(
        timeZone: java.util.TimeZone? = null
    ) : this(
        timeZone?.id ?: "auto"
    )

    companion object {
        val auto = Timezone("auto")
        fun getTimeZone(id: String) =
            Timezone(java.util.TimeZone.getTimeZone(id))

        fun getTimeZone(id: ZoneId) =
            Timezone(java.util.TimeZone.getTimeZone(id))
    }

    val timeZone: java.util.TimeZone?
        get() = java.util.TimeZone.getTimeZone(id)

    override fun toString(): String = id
}
