package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import me.obsilabor.pistonmetakt.annotations.NotWorkingPistonMetaApi
import me.obsilabor.pistonmetakt.annotations.UnsafePistonMetaApi

@Serializable
data class PistonData(
    val arguments: JsonObject, // really scuffed and I don't know what a correct json scheme would look like
    val assetIndex: AssetIndex,
    val assets: String,
    val complianceLevel: Int,
    val downloads: Downloads,
    val id: String,
    val javaVersion: JavaVersion,
    val libraries: List<Library>,
    val logging: Logging,
    val mainClass: String,
    val minimumLauncherVersion: Int,
    val releaseTime: String,
    val time: String,
    val type: String
) {
    @UnsafePistonMetaApi
    @NotWorkingPistonMetaApi
    fun getUnsafeArguments(): LaunchArguments {
        return Json.decodeFromJsonElement(arguments)
    }
}