package me.obsilabor.pistonmetakt.data.launchermeta

import kotlinx.serialization.Serializable
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.obsilabor.pistonmetakt.PistonMetaClient
import me.obsilabor.pistonmetakt.data.pistondata.Downloads
import me.obsilabor.pistonmetakt.data.pistondata.PistonData

@Serializable
data class Version(
    val complianceLevel: Int,
    val id: String,
    val releaseTime: String,
    val sha1: String,
    val time: String,
    val type: String,
    val url: String
) {
    suspend fun getPistonData(): PistonData {
        return PistonMetaClient.ktor.get(url).body<PistonData>()
    }

    suspend fun getDownloads(): Downloads {
        return getPistonData().downloads
    }
}