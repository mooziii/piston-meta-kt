package me.obsilabor.pistonmetakt

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonObject
import me.nullicorn.msmca.minecraft.MinecraftAuth
import me.nullicorn.msmca.minecraft.MinecraftToken
import me.obsilabor.pistonmetakt.PistonMetaClient.ktor

class MicrosoftAuth(private val urlPrefix: String, private val clientId: String, private val clientSecret: String, private val ssl: Boolean = true, private val callback: (MicrosoftAccountInfo) -> Unit) {
    fun setup(port: Int) {
            embeddedServer(Netty, port = port) {
                routing {
                    get("/msauth/done{id,code}") {
                        val minecraft = MinecraftAuth()
                        if (call.parameters["error"] != null) {
                            call.respondText(call.parameters["error"] ?: return@get)
                        }
                        val microsoftAuthJson = ktor.post("https://login.live.com/oauth20_token.srf") {
                            contentType(ContentType.Application.FormUrlEncoded)
                            parameter("client_secret", clientSecret)
                            setBody(
                                FormDataContent(
                                    Parameters.build {
                                        append("client_id", clientId)
                                        append("grant_type", "authorization_code")
                                        append("code", call.parameters["code"] ?: return@get)
                                        append(
                                            "redirect_uri",
                                            "http${if (ssl) "s" else ""}://$urlPrefix/msauth/done?id=${call.parameters["id"] ?: return@get}"
                                        )
                                        append("client_secret", clientSecret)
                                    }
                                )
                            )
                        }.body<JsonObject>()
                        val minecraftAuthResponse = minecraft.loginWithMicrosoft(microsoftAuthJson["access_token"]?.toString()?.removePrefix("\"")?.removeSuffix("\"")
                            ?: return@get)
                        val minecraftAuthInfo = ktor.get("https://api.minecraftservices.com/minecraft/profile") {
                            header("Authorization", "Bearer ${minecraftAuthResponse.value}")
                        }.body<JsonObject>()
                        callback.invoke(
                            MicrosoftAccountInfo(
                                call.parameters["id"] ?: return@get,
                                minecraftAuthResponse,
                                minecraftAuthInfo["id"]?.toString()?.removePrefix("\"")?.removeSuffix("\"") ?: return@get,
                                minecraftAuthInfo["name"]?.toString()?.removePrefix("\"")?.removeSuffix("\"") ?: return@get
                            )
                        )
                        call.respondText("""
                            You may close this tab now
                            <script>
                                window.close();
                            </script>
                        """.trimIndent(), ContentType.Text.Html)
                    }
                }
            }.start(wait = true)
    }

    fun generateURI(id: String): String {
        return "https://login.live.com/oauth20_authorize.srf?client_id=$clientId&response_type=code&scope=XboxLive.signin%20offline_access&state=NOT_NEEDED&redirect_uri=http${if (ssl) "s" else ""}://$urlPrefix/msauth/done?id=${id}"
    }

    data class MicrosoftAccountInfo(val id: String, val token: MinecraftToken, val minecraftUUID: String, val minecraftName: String)
}