package com.openmeteo.apix.common.weather

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializer of [WeatherCode] values
 */
object WeatherCodeSerializer : KSerializer<WeatherCode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("WeatherCode", PrimitiveKind.SHORT)

    override fun serialize(encoder: Encoder, value: WeatherCode) = encoder.encodeShort(value.code)
    override fun deserialize(decoder: Decoder): WeatherCode {
        val code = decoder.decodeFloat().toInt().toShort()
        return WeatherCode.values()
            .firstOrNull { it.code == code } ?: WeatherCode.UNKNOWN
    }
}
