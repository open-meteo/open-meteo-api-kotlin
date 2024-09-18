package com.openmeteo.library

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.Error

@Serializable
public class Error private constructor(
    @SerialName("reason")
    override val message: String?,
    private val error: Boolean = true,
) : Error() {

    // abuse companion object to get a suspendable constructor
    public companion object {

        /**
         * Parse the JSON string as an Error.
         */
        public suspend operator fun invoke(response: HttpResponse): Error =
            Json.decodeFromString(serializer(), response.bodyAsText())

    }
}