package me.obsilabor.pistonmetakt.utils

import me.obsilabor.pistonmetakt.PistonMetaClient
import me.obsilabor.pistonmetakt.data.launchermeta.Version

suspend operator fun Version.compareTo(other: Version): Int {
    val versions = PistonMetaClient.getLauncherMeta().versions
    return when(versions.indexOf(this).compareTo(versions.indexOf(other))) { // invert the result
        -1 -> 1
        1 -> -1
        else -> 0
    }
}

