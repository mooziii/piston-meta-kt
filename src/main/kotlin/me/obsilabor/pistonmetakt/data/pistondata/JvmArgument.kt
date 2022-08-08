package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class JvmArgument(
    val rules: List<JvmRule>,
    val value: List<String>
)