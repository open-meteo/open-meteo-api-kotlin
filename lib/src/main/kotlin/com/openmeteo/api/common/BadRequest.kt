package com.openmeteo.api.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BadRequest(
    val error: Boolean? = true,
    @SerialName("reason")
    override val message: String?,
) : Error()