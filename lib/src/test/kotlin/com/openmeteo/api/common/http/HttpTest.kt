package com.openmeteo.api.common.http

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL
import kotlin.test.Test

class HttpTest {

    companion object {
        val client = Http { connection ->
            if (connection.responseCode == 200)
                connection.inputStream
                    .use { it.bufferedReader().readText() }
            else null
        }
    }

    @Serializable
    data class Get(
        val args: Map<String, String>,
        val headers: Map<String, String>,
        val origin: String,
        val url: String,
    )

    @Test
    fun get() {
        client.get(URL("https://httpbin.org/get"))
            ?.let { Json.decodeFromString<Get>(it) }
            ?.let {
                require(it.args.isEmpty())
                require(it.headers["Host"] == "httpbin.org")
                val ua = requireNotNull(it.headers["User-Agent"])
                require(ua.startsWith("Java/"))
                require(it.url == "https://httpbin.org/get")
            }
    }

}
