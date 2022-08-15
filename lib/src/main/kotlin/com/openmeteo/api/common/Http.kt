package com.openmeteo.api.common

import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * A simple HTTP/S client
 */
fun interface Http<T> {

    /**
     * A simple response callback
     */
    fun response(data: InputStream, code: Int, message: String): T

    /**
     * The response callback wrapper
     */
    private fun response(connection: HttpsURLConnection) =
        response(connection.inputStream, connection.responseCode, connection.responseMessage)

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