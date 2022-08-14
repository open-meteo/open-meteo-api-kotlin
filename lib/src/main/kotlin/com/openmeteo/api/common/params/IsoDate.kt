package com.openmeteo.api.common.params

import java.text.SimpleDateFormat
import java.util.Date

class IsoDate(date: Long) : Date(date) {
    companion object {
        val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    }
    override fun toString(): String =
        format.format(this)
    constructor(date: Date) : this(
        date.time
    )
    constructor(date: String) : this(
        format.parse(date)
    )
}