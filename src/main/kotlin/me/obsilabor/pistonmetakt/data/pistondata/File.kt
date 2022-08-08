package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class File(
    val id: String,
    val sha1: String,
    val size: Int,
    val url: String
)