package com.openmeteo.api.common.time

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat

@Serializable(with = TimeSerializer::class)
class Time(seconds: Long) : java.util.Date(seconds * 1000) {

    companion object Format : SimpleDateFormat("MM-dd hh:mm")

    constructor(seconds: Double) : this(seconds.toLong())

    override fun toString(): String =
        format(this)

}
