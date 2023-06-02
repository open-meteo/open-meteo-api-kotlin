package com.openmeteo.api.common.time

import java.text.SimpleDateFormat

/**
 * A [Date] which is ISO-8601 formatted when converted to string
 *
 * Eases `start_date` and `end_date` params creation
 */
class Date(date: Long) : java.util.Date(date) {

    companion object Format : SimpleDateFormat("yyyy-MM-dd")

    constructor(date: java.util.Date) : this(date.time)

    constructor(date: String) : this(parse(date))

    override fun toString(): String =
        format(this)
}
