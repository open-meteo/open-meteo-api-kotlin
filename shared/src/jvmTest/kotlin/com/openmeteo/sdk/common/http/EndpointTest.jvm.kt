package com.openmeteo.sdk.common.http

import com.openmeteo.sdk.common.Query
import com.openmeteo.sdk.common.Response
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class EndpointTest {

    private val endpoint = Endpoint("https://httpbin.org/get")

    @Serializable
    data class HttpBinResponse(
        val args: Map<String, String>,
        val headers: Map<String, String>,
        val origin: String,
        val url: String,
    ) : Response

    @Serializable
    data class MyQuery(
        val x: Float,
    ) : Query

    @Test
    fun testDecodeJson() {
        val elevation = endpoint.decodeJson<Response.Elevation>("{\"elevation\":1.0}").elevation
        assertEquals(1f, elevation, "Bad JSON elevation response decoding")
    }

    @Test
    fun testGet() = runBlocking {
        val response : HttpBinResponse = endpoint.query(MyQuery(1f))
        assertContentEquals(response.args.toList(), listOf("x" to "1.0"))
    }

}