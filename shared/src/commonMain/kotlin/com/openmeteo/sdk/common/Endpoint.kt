package com.openmeteo.sdk.common

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.request
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.protobuf.ProtoBuf

/**
 * A generic Open-Meteo API endpoint.
 * @param context The URL of the API endpoint.
 */
open class Endpoint(var context: String) {

    init {
        // KTOR doesn't complain if the http(s) protocol isn't specified
        /*require(context.startsWith("http://")
                || context.startsWith("https://")) {
            "The context URL should start with 'http://' or 'https://'."
        }*/
        require(!context.contains('?')) {
            "The context URL cannot already include a query."
        }
    }

    // KTOR multiplatform core client
    private val client = HttpClient()

    /**
     * Decode a JSON [String]
     */
    internal inline fun <reified T> decodeJson(string: String) =
        Json.decodeFromString<T>(string)

    /**
     * Decode a JSON [HttpResponse]
     */
    internal suspend inline fun <reified T> decodeJson(res: HttpResponse) =
        decodeJson<T>(res.bodyAsText())

    /**
     * Decode a ProtoBuf [ByteArray]
     */
    @OptIn(ExperimentalSerializationApi::class)
    internal inline fun <reified T> decodeProtoBuf(byteArray: ByteArray) =
        ProtoBuf.decodeFromByteArray<T>(byteArray)

    /**
     * Decode a ProtoBuf [HttpResponse]
     */
    internal suspend inline fun <reified T> decodeProtoBuf(res: HttpResponse) =
        decodeProtoBuf<T>(res.readBytes())

    /**
     * Decode an [HttpResponse], given the [ContentFormat]
     */
    internal suspend inline fun <reified R : Response> decode(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json
    ): R =
        when (format) {
            ContentFormat.ProtoBuf -> decodeProtoBuf(res)
            ContentFormat.FlatBuffers -> throw Error("Flatbuffers are not yet implemented") // TODO
            ContentFormat.Json -> decodeJson(res)
        }

    /**
     * Handle responses based on their status code:
     * - 200: return the inputStream
     * - 400: parse JSON error as [BadRequest]
     * - else: return a generic error with the url and the response code/message
     * @param res The Ktor HTTP response
     * @param format The response data format
     */
    internal suspend inline fun <reified R : Response> handleResponse(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json
    ): R =
        when (res.status.value) {
            200 -> decode(res, format)
            400 -> throw decodeJson<BadRequest>(res)
            else -> throw Error("`${res.request.url}` response code: ${res.status}")
        }


    /**
     * GET a specific resource and decode it, handling errors.
     * @param query An optional query to encode as "?a=1&b=c" and append after the context
     * @param context The context (base) url to use (defaults to endpoint stored one)
     */
    internal suspend inline fun <reified R : Response, reified Q : Query> query(
        query: Q? = null,
        context: String = this.context
    ) =
        handleResponse<R>(
            // if (query is Query.CommercialLicense && query.apikey != null)
            // TODO: use commercial subdomain?
            client.get(context + Query.encodeToString(query) ),
            // Json default if missing format
            // TODO: what if query is not a content format one? It forces JSON... no bueno!
            (query as? Query.ContentFormat)?.format ?: ContentFormat.Json
        )

    companion object {

        /**
         * JSON parser with snake case naming strategy. Ignores unknown keys.
         */
        @OptIn(ExperimentalSerializationApi::class)
        val Json = Json {
            ignoreUnknownKeys = true
            namingStrategy = JsonNamingStrategy.SnakeCase
        }

    }

}
