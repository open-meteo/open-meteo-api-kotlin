package com.openmeteo.sdk.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An API Bad Http (400) error
 *
 * It inherits from [Error], easing `throw`s
 */
@Serializable
class BadRequest(
    private val error: Boolean,
    @SerialName("reason")
    override val message: String?
) : Error()
