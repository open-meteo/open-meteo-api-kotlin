package com.openmeteo.api.common.serials

import kotlinx.serialization.Serializable
import java.util.*

/**
 * A class to interact with unix timestamps in seconds
 */
@Serializable(with = TimeSerializer::class)
class Time(
    seconds: Long,
) : Date(seconds * 1000) {
    override fun getTime(): Long = super.getTime() / 1000
}