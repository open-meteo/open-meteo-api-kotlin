package com.openmeteo.apix.common.http

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

interface Endpoint : Http<InputStream> {

    val context: URL
    val serializer: DeserializationStrategy<*>

    /**
     * Parse a Json 400 BadRequest error from an [InputStream]
     */
    @ExperimentalSerializationApi
    private fun badRequest(data: InputStream) =
        Json.decodeFromStream(BadRequest.serializer(), data)

    /**
     * Parse some Json from an [InputStream]
     */
    @ExperimentalSerializationApi
    private fun json(data: InputStream) =
        Json.decodeFromStream(serializer, data)

    /**
     * Parse ProtoBuf from an [InputStream]
     */
    @ExperimentalSerializationApi
    private fun protoBuf(data: InputStream) =
        ProtoBuf.decodeFromByteArray(serializer, data.readBytes())

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
            400 -> throw badRequest(connection.errorStream)
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


    @ExperimentalSerializationApi
    fun query(
        params: Map<String, Any?>,
        format: ContentFormat =
            (params["format"] ?: ContentFormat.json) as ContentFormat
    ) = runCatching  { URL(context, queryOf(params)) }
        .mapCatching { get(it) }
        .mapCatching {
            when (format) {
                ContentFormat.json -> json(it)
                ContentFormat.protobuf -> protoBuf(it)
            }
        }

    @ExperimentalSerializationApi
    fun query(vararg params: Pair<String, Any?>) =
        query(mapOf(*params))
}
