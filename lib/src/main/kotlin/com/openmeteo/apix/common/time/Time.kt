package com.openmeteo.apix.common.time

import kotlinx.serialization.Serializable

@Serializable(with = TimeSerializer::class)
class Time(seconds: Long) : java.util.Date(seconds * 1000)
