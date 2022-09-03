package com.openmeteo.apix.common.http

import com.openmeteo.apix.common.query.Query
import com.openmeteo.apix.common.query.QueryContentFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

abstract class Endpoint(
    val context: URL,
) : Http<InputStream>, Query {

    /**
     * Parse JSON from a [String]
     */
    private inline fun <reified T> json(string: String) =
        Json.decodeFromString<T>(string)

    /**
     * Parse JSON from an [InputStream]
     */
    private inline fun <reified T> json(inputStream: InputStream) =
        json<T>(inputStream.use { it.bufferedReader().readText() })

    /**
     * Parse ProtoBuf from an [InputStream]
     */
    @OptIn(ExperimentalSerializationApi::class)
    private inline fun <reified T> protoBuf(inputStream: InputStream) =
        ProtoBuf.decodeFromByteArray<T>(inputStream.readAllBytes())

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
                400 -> throw json<BadRequest>(errorStream)
                else -> throw Error("`$url` response code: $responseCode ($responseMessage)")
            }
        }

    private inline fun <reified T> get(query: Query = this) =
        runCatching { query.toURL(context) }
            .mapCatching { get(it) }

    internal inline operator fun <reified T> invoke(extra: Query? = null) =
        with (extra?.let { this + it } ?: this) {
            get<T>(this).mapCatching {
                if (this is QueryContentFormat && format == ContentFormat.ProtoBuf) protoBuf(it)
                else json<T>(it)
            }
        }
}
