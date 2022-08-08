package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class JvmRule(
    val action: String,
    val os: Os
)
