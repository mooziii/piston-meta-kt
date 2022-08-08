package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class ClientX(
    val argument: String,
    val `file`: File,
    val type: String
)