package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Os(
    val name: String,
    val arch: String? = null,
    val version: String? = null
)