package com.openmeteo.api.common.serials

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.*
import kotlinx.serialization.descriptors.*
import java.util.TimeZone

object TimeZoneSerializer : KSerializer<TimeZone> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TimeZone", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: TimeZone) = encoder.encodeString(value.id)
    override fun deserialize(decoder: Decoder): TimeZone = TimeZone.getTimeZone(decoder.decodeString())
}