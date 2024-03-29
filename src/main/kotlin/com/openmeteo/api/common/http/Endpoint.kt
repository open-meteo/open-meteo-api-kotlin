package com.openmeteo.api.common.http

import com.openmeteo.api.common.Response
import com.openmeteo.api.common.query.Query
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.decodeFromStream
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
) {

    companion object {
        /**
         * The default JSON parser with snake case naming strategy set
         */
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
    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> json(inputStream: InputStream) =
        Json.decodeFromStream<T>(inputStream)

    /**
     * Parse ProtoBuf from an [InputStream]
     */
    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> protoBuf(inputStream: InputStream) =
        ProtoBuf.decodeFromByteArray<T>(inputStream.readAllBytes())

    /**
     * Open a new HTTPS connection
     */
    private fun connect(url: URL) =
        url.openConnection() as HttpsURLConnection

    /**
     * Handle responses based on their status code:
     * - 200: return the inputStream
     * - 400: parse JSON error as [BadRequest]
     * - else: return a generic error with the url and the response code/message
     */
    private fun response(connection: HttpsURLConnection): InputStream =
        with(connection) {
            when (responseCode) {
                200 -> inputStream
                400 -> throw json<BadRequest>(errorStream)
                else -> throw Error("`$url` response code: $responseCode ($responseMessage)")
            }
        }

    /**
     * GET some data from the url
     */
    fun get(url: URL) =
        response(connect(url))

    /**
     * GET, with a [Query], the endpoint context url and parse the response data
     * (from the specified query format or, if undefined, from json)
     */
    inline fun <reified R : Response, reified Q : Query> query(query: Q, context: URL = this.context) =
        Query.toURL(query, context)
            .runCatching {
                // set the "customer-" prefix to the host(name) if an api key was provided
                val prefix = if (query is Query.CommercialLicense && query.apikey != null)
                    "customer-" else "" // TODO: what if context isn't the default one?
                URL(protocol, prefix + host, port, file /* includes query */)
            }
            .mapCatching { get(it) }
            .mapCatching {
                if (query is Query.ContentFormat && query.format == ContentFormat.ProtoBuf)
                    protoBuf<R>(it) else json<R>(it)
            }
}
