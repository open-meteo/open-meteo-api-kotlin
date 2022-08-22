package com.openmeteo.api.common

import com.openmeteo.api.common.params.ContentFormat
import com.openmeteo.api.common.serials.BadRequest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * An API endpoint
 */
abstract class Endpoint(

    /**
     * The context in which the HTTP/S requests should be made
     *
     * Please end the path with a trailing slash `/`, in order to keep the last
     * path segment when appending the query.
     */
    val context: URL

) : Http<InputStream> {

    /**
     * Parse Json from an [InputStream]
     */
    @ExperimentalSerializationApi
    private inline fun <reified T> json(data: InputStream) =
        Json.decodeFromStream<T>(data)

    /**
     * Parse ProtoBuf from an [InputStream]
     */
    @ExperimentalSerializationApi
    private inline fun <reified T> protoBuf(data: InputStream) =
        ProtoBuf.decodeFromByteArray<T>(data.readBytes())

    /**
     * Implement the response callback logic:
     *
     *  - OK (200) should return the raw data
     *  - BAD REQUEST (400) should be parsed as Json
     *  - else throws a generic error
     */
    @ExperimentalSerializationApi
    override fun response(connection: HttpsURLConnection, code: Int) =
        when (code) {
            200 -> connection.inputStream
            400 -> throw json<BadRequest>(connection.errorStream)
            else -> throw Error("Response: ${connection.responseMessage} ($code)")
        }

    /**
     * Create a query string from a map of parameters
     */
    private fun queryOf(params: Map<String, Any?>) =
        if (params.isEmpty()) ""
        else "?" + params
            .mapNotNull { (k, v) -> v?.let { "$k=$v" } }
            .joinToString("&")

    /**
     * Query the endpoint with a map of parameters
     */
    @ExperimentalSerializationApi
    internal inline fun <reified T> query(
        params: Map<String, Any?>,
        format: ContentFormat? = params["format"] as? ContentFormat,
    ) = runCatching { URL(context, queryOf(params)) }
        .mapCatching { get(it) }
        .mapCatching {
            when (format) {
                ContentFormat.json, null -> json<T>(it)
                ContentFormat.protobuf -> protoBuf(it)
            }
        }

    /**
     * Query the endpoint with a list of parameters
     */
    @ExperimentalSerializationApi
    internal inline fun <reified T> query(
        vararg params: Pair<String, Any?>,
    ) = query<T>(mapOf(*params))

}
