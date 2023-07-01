package com.openmeteo.api.common.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * How the content should be encoded
 *
 * Although it's only implemented in the geocoding API, it might
 * appear elsewhere in the future, hence it's in the common params.
 * ([open-meteo #86](https://github.com/open-meteo/open-meteo/issues/86))
 */
@Serializable
enum class ContentFormat {
    @SerialName("json")
    Json,

    @SerialName("protobuf")
    ProtoBuf,
}
