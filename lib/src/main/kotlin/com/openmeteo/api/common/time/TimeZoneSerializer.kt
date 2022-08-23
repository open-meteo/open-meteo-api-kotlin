package com.openmeteo.api.common.time

import com.openmeteo.api.common.time.TimeZone
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Get a [TimeZone] from a string like `"Europe/Berlin"`
 */
object TimeZoneSerializer : KSerializer<TimeZone> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TimeZone", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TimeZone) = encoder.encodeString(value.id)
    override fun deserialize(decoder: Decoder): TimeZone = TimeZone(decoder.decodeString())
}
