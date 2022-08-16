package com.openmeteo.api.common.serials

import kotlinx.serialization.Serializable
import java.util.*

@Serializable(with = TimeSerializer::class)
class Time(
    seconds: Long,
) : Date(seconds*1000) {
    override fun getTime(): Long = super.getTime()/1000
}