package com.openmeteo.api.common.time

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * How timestamps should be formatted
 *
 * Used internally to hardcode unix [Long] values, as easier/safer to parse
 */
@Serializable
enum class TimeFormat {
    @SerialName("iso8601")
    Iso8601,

    @SerialName("unixtime")
    UnixTime,
}
