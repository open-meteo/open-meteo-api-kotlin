package com.openmeteo.api.common.http

import com.openmeteo.api.common.Response
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HttpTest {

    companion object {
        /**
         * An HTTP client that returns the body string if the response code is 200,
         * else return null
         */
        val client = Http { connection ->
            if (connection.responseCode == 200)
                connection.inputStream
                    .use { it.bufferedReader().readText() }
            else null
        }
    }

    /**
     * The response body of "https://httpbin.org/get"
     */
    @Serializable
    class R(
        val args: Map<String, String>,
        val headers: Map<String, String>,
        val url: String,
    ) : Response

    /**
     * Test a get request to "https://httpbin.org/get"
     */
    @Test
    fun get() {
        val body = client.get(URL("https://httpbin.org/get"))
        assertNotNull(body)
        val response = Json.decodeFromString<R>(body)
        response.run {
            assert(args.isEmpty())
            assertEquals("httpbin.org", headers["Host"])
            assertNotNull(headers["User-Agent"]?.startsWith("Java/"))
            assertEquals(url, "https://httpbin.org/get")
        }
    }

}
