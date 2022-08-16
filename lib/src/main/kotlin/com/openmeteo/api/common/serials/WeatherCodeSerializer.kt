package com.openmeteo.api.common.serials

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.*
import kotlinx.serialization.descriptors.*

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