package com.openmeteo.api.common.serials

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An API Bad Request (400) error object
 *
 * It inherits from [Error], easing `throw`s
 */
@Serializable
data class BadRequest(
    val error: Boolean? = true,
    @SerialName("reason")
    override val message: String?,
) : Error()
