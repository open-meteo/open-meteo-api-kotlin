package com.openmeteo.api.common.time

import kotlinx.serialization.Serializable
import java.util.*

/**
 * A [SimpleTimeZone] class wrapper
 *
 * The constructor uses [java.util.TimeZone.getTimeZone] to resolve arguments
 * Please note that "auto" is a valid string id
 */
@Serializable(with = TimeZoneSerializer::class)
class TimeZone(rawOffset: Int, id: String) : SimpleTimeZone(rawOffset, id) {
    constructor(id: String = "auto") : this(0, id) {
        if (id == "auto") return
        val tz = java.util.TimeZone.getTimeZone(id)
        this.rawOffset = tz.rawOffset
        this.id = tz.id
    }

    constructor(zoneId: java.time.ZoneId) : this(0, zoneId.id) {
        val tz = java.util.TimeZone.getTimeZone(zoneId)
        this.rawOffset = tz.rawOffset
    }

    override fun toString(): String = id
}
