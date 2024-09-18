package com.openmeteo.library

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsBytes
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.bits.reverseByteOrder
import io.ktor.utils.io.readByteArray
import io.ktor.utils.io.readInt
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import kotlinx.serialization.serializer

private inline fun <reified Q : Query<Q, R>, reified R> query(
    url: String,
    format: String,
) : Query<Q, R> = object : Query<Q, R> {
    override val client: HttpClient = HttpClient { defaultRequest { url(url) }}
    override val format: String = format
    override val serializer: SerializationStrategy<Q> = serializer()
    override val deserializer: DeserializationStrategy<R>? = null
    override suspend fun decode(response: HttpResponse): List<R> = TODO()
}

internal object Decoding {

    internal inline fun <reified Q : Query<Q, R>, reified R> json(
        from: String,
    ): Query<Q, R> = object : Query<Q, R> by query(from, "json") {
        override val deserializer: DeserializationStrategy<R> = serializer()
        override suspend fun decode(response: HttpResponse): List<R> =
            // TODO: implement proper lists
            listOf(Json.decodeFromString(this.deserializer, response.bodyAsText()))
    }

    @OptIn(ExperimentalSerializationApi::class)
    internal inline fun <reified Q : Query<Q, R>, reified R> protobuf(
        from: String,
    ): Query<Q, R> = object : Query<Q, R> by query(from, " protobuf") {
        override val deserializer: DeserializationStrategy<R> = serializer()
        override suspend fun decode(response: HttpResponse): List<R> =
            // TODO: implement proper lists
            listOf(ProtoBuf.decodeFromByteArray(this.deserializer, response.bodyAsBytes()))
    }


    internal inline fun <reified Q : Query<Q, R>, reified R> flatbuffers(
        from: String,
        crossinline decode: (bytes: ByteArray) -> R
    ): Query<Q, R> = object : Query<Q, R> by query(from, " flatbuffers") {
        override suspend fun decode(response: HttpResponse): List<R> {
            val channel = response.bodyAsChannel()
            return buildList {
                while (!channel.isClosedForRead) {

                    // Ktor BE Int --> FlatBuffer LE Int
                    val size = channel.readInt().reverseByteOrder()
                    val bytes = channel.readByteArray(size)

                    // TODO: what if the byte array is not long enough?!

                    // Add entry to the list
                    val entry = decode(bytes)
                    add(entry)

                }
            }
        }
    }

}