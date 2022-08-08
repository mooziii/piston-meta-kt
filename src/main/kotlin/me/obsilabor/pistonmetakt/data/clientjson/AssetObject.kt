package me.obsilabor.pistonmetakt.data.clientjson

import kotlinx.serialization.Serializable

@Serializable
data class AssetObject(
    val hash: String,
    val size: Long
)