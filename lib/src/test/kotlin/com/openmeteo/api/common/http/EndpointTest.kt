package com.openmeteo.api.common.http

import com.openmeteo.api.common.Response
import com.openmeteo.api.common.query.Query
import kotlinx.serialization.Serializable
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals

class EndpointTest {

    /**
     * The query
     */
    @Serializable
    class Q(
        override val latitude: Float,
        override val longitude: Float,
    ) : Query.Coordinate

    /**
     * The response body
     */
    @Serializable
    class R(
        val args: Q,
        val url: String,
    ) : Response

    /**
     * Test a get request to "https://httpbin.org/get"
     */
    @Test
    fun get() {
        val endpoint = Endpoint(URL("https://httpbin.org/get"))
        val query = Q(-1.313f, 3.1415f)
        val response = endpoint.query<R, Q>(query).getOrThrow()
        response.run {
            assertEquals(-1.313f, args.latitude)
            assertEquals(3.1415f, args.longitude)
            assertEquals("https://httpbin.org/get?latitude=-1.313&longitude=3.1415", url)
        }
    }

}
