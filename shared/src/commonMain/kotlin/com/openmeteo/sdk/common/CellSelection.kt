package com.openmeteo.sdk.common

import kotlinx.serialization.SerialName

/**
 * Preference how grid-cells are selected
 */
enum class CellSelection {
    @SerialName("land")
    Land,

    @SerialName("sea")
    Sea,

    @SerialName("nearest")
    Nearest,
}
