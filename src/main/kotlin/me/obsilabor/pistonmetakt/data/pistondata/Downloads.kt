package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Downloads(
    val client: Client,
    val client_mappings: ClientMappings,
    val server: Server,
    val server_mappings: ServerMappings
)