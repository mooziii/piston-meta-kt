package me.obsilabor.pistonmetakt

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.obsilabor.pistonmetakt.annotations.NotWorkingPistonMetaApi
import me.obsilabor.pistonmetakt.data.launchermeta.LauncherMeta

object PistonMetaClient {
    val ktor = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getLauncherMeta(): LauncherMeta {
        return ktor.get("https://launchermeta.mojang.com/mc/game/version_manifest_v2.json").body()
    }
}