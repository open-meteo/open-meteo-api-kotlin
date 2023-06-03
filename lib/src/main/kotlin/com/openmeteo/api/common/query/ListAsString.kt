package com.openmeteo.api.common.query

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializer of [List] (for queries)
 */
object ListAsString : KSerializer<List<*>> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ListAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: List<*>) =
        encoder.encodeString(value.joinToString(","))
    override fun deserialize(decoder: Decoder): List<*> {
        val text = decoder.decodeString()
        /**
         * Comma is going to be URL encoded, but no worries, it works fine since
         * text is decoded before the list splitting on the server!
         */
        return text.split(",")
    }
}
