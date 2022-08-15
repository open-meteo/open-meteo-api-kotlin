package com.openmeteo.api.common.serials

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.*
import kotlinx.serialization.descriptors.*
import java.util.Date

object TimeSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeLong(value.time/1000)
    override fun deserialize(decoder: Decoder): Date = Date(decoder.decodeLong()*1000)
}