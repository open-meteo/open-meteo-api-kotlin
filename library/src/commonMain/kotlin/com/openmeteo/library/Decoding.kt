package com.openmeteo.library

import com.google.flatbuffers.kotlin.ArrayReadWriteBuffer
import com.google.flatbuffers.kotlin.ReadWriteBuffer
import com.google.flatbuffers.kotlin.Table
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsBytes
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.bits.reverseByteOrder
import io.ktor.utils.io.readByteArray
import io.ktor.utils.io.readInt
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf

internal object Decoding {

    /**
     * Create an [Expect] object that can decode an [R] Json response
     */
    internal inline fun <reified R : @Serializable Any> json(): Expect<R> =
        object : Expect<R> {
            override val format: String = "json"
            override suspend fun decode(response: HttpResponse): List<R> =
                if (this.expectedElements == 1)
                    listOf(Json.decodeFromString(response.bodyAsText()))
                else Json.decodeFromString(response.bodyAsText())
        }

    /**
     * Create an [Expect] object that can decode an [R] ProtoBuf response
     */
    @OptIn(ExperimentalSerializationApi::class)
    internal inline fun <reified R : @Serializable Any> protoBuf(): Expect<R> =
        object : Expect<R> {
            override val format: String = "protobuf"
            override suspend fun decode(response: HttpResponse): List<R> =
                if (this.expectedElements == 1)
                    listOf(ProtoBuf.decodeFromByteArray(response.bodyAsBytes()))
                else ProtoBuf.decodeFromByteArray(response.bodyAsBytes())
        }

    /**
     * Create an [Expect] object that can decode an [R] FlatBuffers response
     */
    internal inline fun <reified R : Table> flatBuffers(
        crossinline asRoot: (buffer: ReadWriteBuffer) -> R
    ): Expect<R> =
        object : Expect<R> {
            override val format: String = "flatbuffers"
            override suspend fun decode(response: HttpResponse): List<R> =
                buildList {
                    val channel = response.bodyAsChannel()
                    while (!channel.isClosedForRead) {

                        // Ktor BE Int --> FlatBuffer LE Int
                        val size = channel.readInt().reverseByteOrder()
                        val bytes = channel.readByteArray(size)
                        val buffer = ArrayReadWriteBuffer(bytes)

                        // Add entry to the list
                        val entry = asRoot(buffer)
                        add(entry)
                    }
                }
        }

}