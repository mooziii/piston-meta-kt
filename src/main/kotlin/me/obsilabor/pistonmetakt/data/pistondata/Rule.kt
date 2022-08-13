package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    val action: String? = null,
    val os: Os? = null
)