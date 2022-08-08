package me.obsilabor.pistonmetakt.data.pistondata

import kotlinx.serialization.Serializable

@Serializable
data class Library(
    val downloads: DownloadsX,
    val name: String,
    val rules: List<Rule> = emptyList()
)