package com.openmeteo.apix.common.http

import com.openmeteo.apix.common.query.Query
import com.openmeteo.apix.common.query.QueryContentFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

abstract class Endpoint(
    private val context: URL
) : Http<InputStream>, Query {

    /**
     * An API Bad Request (400) error object
     *
     * It inherits from [Error], easing `throw`s
     */
    @Serializable
    class BadRequest(
        private val error: Boolean,
        @SerialName("reason")
        override val message: String?
    ) : Error()

    /**
     * Parse JSON from a [String]
     */
    internal fun <R : @Serializable Any> json(
        string: String,
        deserializationStrategy: DeserializationStrategy<R>,
    ) = Json.decodeFromString(deserializationStrategy, string)

    /**
     * Parse JSON from an [InputStream]
     */
    internal fun <R : @Serializable Any> json(
        inputStream: InputStream,
        deserializationStrategy: DeserializationStrategy<R>,
    ) = json(inputStream.use { it.bufferedReader().readText() }, deserializationStrategy)

    /**
     * Handle responses based on their status code:
     * - 200: return the inputStream
     * - 400: parse JSON error as [BadRequest]
     * - else: return a generic error with the url and the response code/message
     */
    override fun response(connection: HttpsURLConnection): InputStream =
        with (connection) {
            when (responseCode) {
                200 -> inputStream
                400 -> throw json(errorStream, BadRequest.serializer())
                else -> throw Error("\"$url\" returned $responseCode ($responseMessage)")
            }
        }

    /**
     * Get a query URL with non-extension properties declared in `obj`.
     * By default `this` is used.
     */
    fun query(obj: Query = this) =
        runCatching { obj.apply(context) }

    /**
     * Query the endpoint with non-extension properties declared in `obj`.
     * By default `this` is used.
     */
    fun invoke(obj: Query = this) = query(obj)
        .mapCatching { get(it) }

}
