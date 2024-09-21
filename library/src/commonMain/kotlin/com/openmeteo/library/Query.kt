package com.openmeteo.library

import com.openmeteo.library.serializers.ListAsString
import com.openmeteo.sdk.Variable
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Transient
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.serializer

/**
 * A serializable query
 */
@Serializable
public abstract class Query internal constructor(
    @Transient
    public val client: HttpClient = HttpClient()
) {

    public constructor(url: String) : this(
        HttpClient { defaultRequest { url(url) } }
    )

    /**
     * Encode the query as a Map
     */
    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    @Suppress("UNCHECKED_CAST")
    private fun toMap(): Map<String, String> =
        Properties.encodeToStringMap(this::class.serializer() as SerializationStrategy<Query>, this)

    /**
     * Perform a GET request with this [Query]
     */
    internal suspend fun query(httpClient: HttpClient, url: String = ""): HttpResponse {

        // obtain a (mutable) map representing the query
        val map = toMap().toMutableMap()

        // manually append keys assigned through delegation
        if (this is Expect<*>) {
            map["format"] = this.format
        }

        // perform GET request
        return httpClient.get(url) {
            map.forEach { (k, v) -> this.url.parameters.append(k, v) }
        }

    }

    /**
     * A [Query] for a single location
     */
    internal interface Coordinates {
        public val latitude: Float
        public val longitude: Float

        /**
         * A [Query] for a multiple locations
         */
        public interface Multiple {
            public val latitude: ListAsString<Float>
            public val longitude: ListAsString<Float>
        }
    }

    /**
     * A [Query] for hourly data
     */
    internal interface Hourly {
        public val hourly: ListAsString<String>
    }

}