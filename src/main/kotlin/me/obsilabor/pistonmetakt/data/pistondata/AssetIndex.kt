package me.obsilabor.pistonmetakt.data.pistondata

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import me.obsilabor.pistonmetakt.PistonMetaClient
import me.obsilabor.pistonmetakt.data.clientjson.AssetObject
import me.obsilabor.pistonmetakt.data.clientjson.ClientAssets

@Serializable
data class AssetIndex(
    val id: String,
    val sha1: String,
    val size: Int,
    val totalSize: Int,
    val url: String
) {
    suspend fun getObjects(): HashMap<String, AssetObject> {
        return PistonMetaClient.ktor.get(url).body<ClientAssets>().objects
    }
}