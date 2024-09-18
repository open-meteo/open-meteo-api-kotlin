package com.openmeteo.library

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.properties.Properties

internal interface Query<Q : Query<Q, R>, R> {
    public val client: HttpClient
    public val format: String
    public val serializer: SerializationStrategy<Q>
    public val deserializer: DeserializationStrategy<R>?
    public suspend fun decode(response: HttpResponse) : List<R>

    /**
     * Cast this query to a key-value flat map
     */
    @OptIn(ExperimentalSerializationApi::class)
    public fun <Q : Query<Q, *>> Q.toMap() : Map<String, String> =
        Properties.encodeToStringMap(serializer, this)

    /**
     * Perform a GET request using the default client and this query
     */
    public suspend fun <Q : Query<Q, *>> Q.get() : HttpResponse =
        client.get {

            // Prepare query as mutable map
            val map = toMap().toMutableMap()

            // Append format (delegated properties are not serialized)
            map["format"] = format

            // Append query parameters, one by one
            map.forEach { (k, v) ->
                // TODO: fix elevation endpoint not accepting %2C encoded commas
                // this.url.parameters.append(k, v)
                this.url.encodedParameters.append(k, v)
            }

        }
}