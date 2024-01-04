package com.openmeteo.sdk.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * How the content should be encoded
 *
 * See [open-meteo #86](https://github.com/open-meteo/open-meteo/issues/86)
 */
@Serializable
enum class ContentFormat {
    @SerialName("json")
    Json,

    @SerialName("protobuf")
    ProtoBuf,

    @SerialName("flatbuffers")
    FlatBuffers,
}
