package com.openmeteo.api.common.time

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TimeSerializer : KSerializer<Time> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Time", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: Time) = encoder.encodeLong(value.time / 1000)
    override fun deserialize(decoder: Decoder): Time = Time(decoder.decodeLong())
}
