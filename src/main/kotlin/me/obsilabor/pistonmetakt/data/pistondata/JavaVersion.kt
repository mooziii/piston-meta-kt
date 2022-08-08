package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class JavaVersion(
    val component: String,
    val majorVersion: Int
)