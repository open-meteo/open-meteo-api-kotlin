package com.openmeteo.apix.common.http

import java.net.URL
import javax.net.ssl.HttpsURLConnection

interface Http<T> {

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
     * GET some data from the url
     */
    fun get(url: URL) =
        response(connect(url))
}
