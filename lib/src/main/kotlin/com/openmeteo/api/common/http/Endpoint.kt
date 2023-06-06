package com.openmeteo.api.common.http

import com.openmeteo.api.common.Response
import com.openmeteo.api.common.query.Query
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * An API endpoint with a context [URL] which can automatically parse response
 * data of a GET request with a [Query]
 */
open class Endpoint(
    @Transient
    val context: URL,
) : Http<InputStream> {

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        val Json = Json {
            ignoreUnknownKeys = true
            namingStrategy = JsonNamingStrategy.SnakeCase
        }
    }

    /**
     * Parse JSON from a [String]
     */
    inline fun <reified T> json(string: String) =
        Json.decodeFromString<T>(string)

    /**
     * Parse JSON from an [InputStream]
     */
    inline fun <reified T> json(inputStream: InputStream) =
        json<T>(inputStream.use { it.bufferedReader().readText() })

    /**
     * Parse ProtoBuf from an [InputStream]
     */
    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> protoBuf(inputStream: InputStream) =
        ProtoBuf.decodeFromByteArray<T>(inputStream.readAllBytes())

    /**
     * Handle responses based on their status code:
     * - 200: return the inputStream
     * - 400: parse JSON error as [BadRequest]
     * - else: return a generic error with the url and the response code/message
     */
    override fun response(connection: HttpsURLConnection): InputStream =
        with(connection) {
            when (responseCode) {
                200 -> inputStream
                400 -> throw json<BadRequest>(errorStream)
                else -> throw Error("`$url` response code: $responseCode ($responseMessage)")
            }
        }

    /**
     * GET, with a [Query], the endpoint context url and parse the response data
     * (from the specified query format or, if undefined, from json)
     */
    inline fun <reified R : Response, reified Q : Query> query(query: Q) =
        runCatching { Query.toURL(query, context) }
            .mapCatching { get(it) }
            .mapCatching {
                if (query is Query.ContentFormat && query.format == ContentFormat.ProtoBuf)
                    protoBuf<R>(it)
                else json<R>(it)
            }
}
