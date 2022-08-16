package com.openmeteo.api.common.serials

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.*
import kotlinx.serialization.descriptors.*
import java.util.Date

object TimeSerializer : KSerializer<Time> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Time", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Time) = encoder.encodeLong(value.time)
    override fun deserialize(decoder: Decoder): Time = Time(decoder.decodeLong())
}