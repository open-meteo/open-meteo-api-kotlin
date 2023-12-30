package com.openmeteo.sdk.common.http

import com.openmeteo.sdk.common.Response
import com.openmeteo.sdk.common.Query
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.protobuf.ProtoBuf

/**
 * An API endpoint with a context URL which can automatically parse response
 * data of a GET request with a [Query]
 */
open class Endpoint(
    @Transient
    val context: String,
) {

    // The Ktor HTTP client
    val client = HttpClient()

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
     * Decode a JSON [String]
     */
    inline fun <reified T> decodeJson(string: String) =
        Json.decodeFromString<T>(string)

    /**
     * Decode a JSON [HttpResponse]
     */
    suspend inline fun <reified T> decodeJson(res: HttpResponse) =
        decodeJson<T>(res.bodyAsText())

    /**
     * Decode a ProtoBuf [ByteArray]
     */
    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> decodeProtoBuf(byteArray: ByteArray) =
        ProtoBuf.decodeFromByteArray<T>(byteArray)

    /**
     * Decode a ProtoBuf [HttpResponse]
     */
    suspend inline fun <reified T> decodeProtoBuf(res: HttpResponse) =
        decodeProtoBuf<T>(res.readBytes())

    suspend inline fun <reified R : Response> decode(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json
    ) : R =
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
    suspend inline fun <reified R : Response> handleResponse(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json
    ) : R =
        when (res.status.value) {
            200 -> decode(res, format)
            400 -> throw decodeJson<BadRequest>(res) // or decode<T>(..., Json)
            else -> throw Error("`${res.request.url}` response code: ${res.status}")
        }

    /**
     * GET a specific resource and decode it, handling errors
     * @param query A query to encode as "?a=1&b=c" and append after the context
     * @param context The context (base) url to use
     */
    suspend inline fun <reified R : Response, reified Q : Query> query(
        query: Q? = null,
        context: String = this.context
    ) : R =
        // if (query is Query.CommercialLicense && query.apikey != null) // TODO: use commercial subdomain?
        // Json if missing format, TODO: what if query is not a content format one? It tries to parse JSON... Feature? Idk
        handleResponse(
            client.get(context+(query?.let { Query.asString(it) } ?: "")),
            (query as? Query.ContentFormat)?.format ?: ContentFormat.Json
        )

}
