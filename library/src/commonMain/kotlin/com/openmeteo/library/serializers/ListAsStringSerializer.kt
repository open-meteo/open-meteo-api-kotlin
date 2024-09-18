package com.openmeteo.library.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encoding.Encoder

public class ListAsStringSerializer<T>(private val dataSerializer: KSerializer<T>) : KSerializer<List<T>>
by ListSerializer(dataSerializer) {

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: List<T>) {
        val listEncoder = ListAsStringEncoder()
        value.forEach { dataSerializer.serialize(listEncoder, it) }
        val string = listEncoder.list.joinToString(",")
        encoder.encodeString(string)
    }

}