package com.openmeteo.apix.common.http

import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * A simple HTTP/S client
 */
fun interface Http<out T> {

    /**
     * A simple response callback
     */
    fun response(connection: HttpsURLConnection): T

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
