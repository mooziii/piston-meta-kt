package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class LaunchArguments(
    val game: List<String>,
    val jvm: List<JvmArgument>
)
