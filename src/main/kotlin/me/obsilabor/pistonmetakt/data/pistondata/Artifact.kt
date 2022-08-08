package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Artifact(
    val path: String,
    val sha1: String,
    val size: Int,
    val url: String
)