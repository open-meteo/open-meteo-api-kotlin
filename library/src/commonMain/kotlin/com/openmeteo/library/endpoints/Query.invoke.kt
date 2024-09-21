package com.openmeteo.library.endpoints

import com.openmeteo.library.Error
import com.openmeteo.library.Expect
import com.openmeteo.library.Query
import io.ktor.http.isSuccess

/**
 * Given an instance of a [Query] of type [Q] and an [Expect] of type [R], perform a GET request and
 * decode [R].
 */
public suspend operator fun <Q, R> Q.invoke(url: String = ""): List<R> where Q : Query, Q : Expect<R> {

    // perform the HTTP GET query
    val response = query(client, url)

    // decode if status code is within 200-299 range, else throw error
    if (response.status.isSuccess()) {
        return decode(response)
    } else {
        throw Error(response)
    }
}
