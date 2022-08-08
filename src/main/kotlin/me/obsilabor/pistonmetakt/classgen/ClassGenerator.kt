package me.obsilabor.pistonmetakt.classgen

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import me.obsilabor.pistonmetakt.PistonMetaClient
import me.obsilabor.pistonmetakt.data.launchermeta.Version
import java.io.File
import kotlin.text.StringBuilder

suspend fun main(args: Array<String>) {
    val fileName = "src/main/kotlin/me/obsilabor/pistonmetakt/MinecraftVersions.kt"
    print("[ ] resolving versions")
    val versions = PistonMetaClient.getLauncherMeta().versions
    print("\r[✔] resolved versions")
    val file = FileSpec.builder("me.obsilabor.pistonmetakt", "MinecraftVersions")
    val typeSpec = TypeSpec.classBuilder("MinecraftVersions")
        .addAnnotation(AnnotationSpec.builder(Suppress::class).addMember("\"unused\", \"PropertyName\"").build())
    println()
    print("[ ] generating file")
    for (version in versions) {
        typeSpec.addProperty(
            PropertySpec.builder(
                "V_" +
                version.id.uppercase()
                    .replace(".", "_")
                    .replace("-", "_")
                    .replace(" ", "_"), Version::class)
                .initializer(version.sha1)
                .addKdoc("This represents the minecraft version ${version.id}")
                .build()
        ).build()
    }
    file.addType(typeSpec.build())
    val builder = StringBuilder()
    file.build().writeTo(builder)
    print("\r[✔] generated file")
    println()
    print("[ ] post processing the file")
    var fileString = builder.toString()
    fileString = fileString
        .replace("`", "")
        .replace("public ", "")
    for (version in versions) {
        fileString = fileString.replace("${version.sha1}", "Version(${version.complianceLevel}, \"${version.id}\", \"${version.releaseTime}\", \"${version.sha1}\", \"${version.time}\", \"${version.type}\", \"${version.url}\")")
    }
    print("\r[✔] post processing the file")
    println()
    print("[ ] writing to disk")
    File(fileName).writeText(fileString)
    print("\r[✔] writing to disk")
    if (!args.contains("--no-commit")) {
       ProcessBuilder("git", "add", fileName).redirectErrorStream(true).redirectInput(ProcessBuilder.Redirect.INHERIT).redirectOutput(
           ProcessBuilder.Redirect.INHERIT).start()
        ProcessBuilder("git", "commit", "-m", "\"Update MinecraftVersions via launchermeta API\"").redirectErrorStream(true).redirectInput(ProcessBuilder.Redirect.INHERIT).redirectOutput(
            ProcessBuilder.Redirect.INHERIT).start()
    }
}