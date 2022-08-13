plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("application")
}

group = "me.obsilabor"
version = "1.0.0"

application {
    mainClass.set("$group.pistonmetakt.classgen.ClassGeneratorKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("io.ktor:ktor-client-core:2.1.0")
    implementation("io.ktor:ktor-client-cio:2.1.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.0")
}

