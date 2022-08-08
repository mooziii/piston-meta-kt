package me.obsilabor.pistonmetakt.data.launchermeta

import kotlinx.serialization.Serializable

@Serializable
data class LauncherMeta(
    val latest: Latest,
    val versions: List<Version>
)