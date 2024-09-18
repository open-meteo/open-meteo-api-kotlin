package com.openmeteo.library.endpoints

import com.openmeteo.library.Query


/**
 * Perform query and decode its output
 */
public suspend operator fun <Q : Query<Q, R>, R> Q.invoke() : List<R> =
    decode(get())
