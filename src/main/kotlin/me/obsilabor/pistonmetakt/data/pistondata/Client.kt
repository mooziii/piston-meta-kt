package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val sha1: String,
    val size: Int,
    val url: String
)