package me.obsilabor.pistonmetakt.data.launchermeta

import kotlinx.serialization.Serializable

@Serializable
data class Latest(
    val release: String,
    val snapshot: String
)