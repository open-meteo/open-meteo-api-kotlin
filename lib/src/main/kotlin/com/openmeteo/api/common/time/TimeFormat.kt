package com.openmeteo.api.common.time

/**
 * How timestamps should be formatted
 *
 * Used internally to hardcode unix [Long] values, as easier/safer to parse
 */
enum class TimeFormat {
    iso8601,
    unixtime,
}
