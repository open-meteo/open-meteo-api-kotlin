package com.openmeteo.api.common.time

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat

/**
 * A [Date] which is ISO-8601 formatted when converted to string.
 *
 * Eases `start_date` and `end_date` params creation.
 *
 * @param date The unix timestamp in seconds
 */
@Serializable(with = DateSerializer::class)
class Date(date: Long) : java.util.Date(date) {

    /**
     * The `yyyy-MM-dd` ISO date formatter
     */
    companion object Format : SimpleDateFormat("yyyy-MM-dd")

    /**
     * @param date A more "common" [java.util.Date]
     */
    constructor(date: java.util.Date) : this(date.time)

    /**
     * @param date An ISO `yyyy-MM-dd` date to parse
     * @throws java.text.ParseException If unable to parse the ISO date
     */
    constructor(date: String) : this(parse(date))

    /**
     * Format this date as an ISO `yyyy-MM-dd` date
     */
    override fun toString(): String =
        format(this)
}
