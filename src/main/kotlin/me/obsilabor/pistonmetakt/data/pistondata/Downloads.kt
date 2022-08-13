package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Downloads(
    val client: Client,
    /**
     * Only present on 1.14 and above
     */
    val client_mappings: ClientMappings? = null,
    val server: Server,
    /**
     * Only present on 1.14 and above
     */
    val server_mappings: ServerMappings? = null
)