package com.openmeteo.api.common

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.net.URL

interface Endpoint : Http<InputStream> {

    val context: URL

    /**
     * Parse Json from an [InputStream]
     */
    private inline fun <reified T> json(data: InputStream) =
        data.bufferedReader().use {
            Json.decodeFromString<T>(it.readText())
        }

    /**
     * Implement the response callback logic:
     *
     *  - OK (200) should return the raw data
     *  - BAD REQUEST (400) should be parsed as Json
     *  - else throws a generic error
     */
    override fun response(data: InputStream, code: Int, message: String) =
        when (code) {
            200 -> data
            400 -> throw json<BadRequest>(data)
            else -> throw Error("Response: $message ($code)")
        }

    /**
     * Create a query string from a list of parameters
     */
    private fun queryOf(vararg params: Pair<String, Any?>) =
        if (params.isEmpty()) ""
        else "?" + params
            .mapNotNull { (k,v) -> v?.let { "$k=$v" } }
            .joinToString("&")

    /**
     * Query the endpoint with a list of parameters
     */
    fun query(
        vararg params: Pair<String, Any?>,
    ) = runCatching { URL(context, queryOf(*params)) }
        .mapCatching { get(it) }

}