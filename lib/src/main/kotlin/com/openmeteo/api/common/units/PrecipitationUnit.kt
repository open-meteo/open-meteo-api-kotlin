package com.openmeteo.api.common.units

/**
 * The unit used for precipitations
 *
 * When used as an endpoint parameter cm "becomes" mm through `?.param()`
 */
enum class PrecipitationUnit {
    cm,
    mm,
    inch;

    /**
     * Called only when the unit is used as parameter
     */
    fun param() =
        takeUnless { it == cm } ?: mm
}
