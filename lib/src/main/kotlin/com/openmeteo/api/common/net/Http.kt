package com.openmeteo.api.common.net

import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * A simple HTTP/S client
 */
fun interface Http<T> {

    /**
     * A simple response callback
     */
    fun response(connection: HttpsURLConnection, code: Int): T

    /**
     * The response callback wrapper
     */
    private fun response(connection: HttpsURLConnection) =
        response(connection, connection.responseCode)

    /**
     * Open a new HTTPS connection
     */
    private fun connect(url: URL) =
        url.openConnection() as HttpsURLConnection

    /**
     * Perform a GET request
     */
    fun get(url: URL) =
        response(connect(url))

    // we could implement more methods/logic, but we don't need them :)
}
