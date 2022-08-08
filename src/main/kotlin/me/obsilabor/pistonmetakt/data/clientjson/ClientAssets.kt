package me.obsilabor.pistonmetakt.data.clientjson

import kotlinx.serialization.Serializable

@Serializable
data class ClientAssets(
    val objects: HashMap<String, AssetObject>
)