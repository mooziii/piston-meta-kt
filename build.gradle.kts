plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("application")
    id("java-library")
    id("maven-publish")
    id("signing")
}

group = "me.obsilabor"
version = "1.0.5"

application {
    mainClass.set("$group.pistonmetakt.classgen.ClassGeneratorKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.12.0")

    implementation("io.ktor:ktor-client-core:2.1.3")
    implementation("io.ktor:ktor-client-cio:2.1.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.3")

    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("io.ktor:ktor-server-core:2.1.3")
    implementation("io.ktor:ktor-server-netty:2.1.3")

    implementation("me.nullicorn:ms-to-mca:0.0.1")
}

signing {
    sign(publishing.publications)
}

java {
    withSourcesJar()
    withJavadocJar()
}

val jarThing by tasks.registering(Jar::class) {
    archiveClassifier.set("jar")
}

publishing {
    kotlin.runCatching {
        repositories {
            maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
                name = "ossrh"
                credentials(PasswordCredentials::class) {
                    username = (property("ossrhUsername") ?: return@credentials) as String
                    password = (property("ossrhPassword") ?: return@credentials) as String
                }
            }
            maven("https://repo.obsilabor.me/snapshots") {
                name = "obsilaborRepoSnapshots"
                credentials(PasswordCredentials::class) {
                    username = (property("obsilaborRepoUsername") ?: return@credentials) as String
                    password = (property("obsilaborRepoPassword") ?: return@credentials) as String
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }.onFailure {
        println("Unable to add publishing repositories: ${it.message}")
    }

    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
            artifact(jarThing.get())

            this.groupId = project.group.toString()
            this.artifactId = project.name.toLowerCase()
            this.version = project.version.toString()

            pom {
                name.set(project.name)
                description.set("Kotlin library to interact with mojangs launchermeta and \"piston-data\" api")

                developers {
                    developer {
                        name.set("mooziii")
                    }
                }

                licenses {
                    license {
                        name.set("GPL-3.0 License")
                        url.set("https://github.com/mooziii/piston-meta-kt/blob/master/LICENSE")
                    }
                }

                url.set("https://github.com/mooziii/piston-meta-kt")

                scm {
                    connection.set("scm:git:git://github.com/mooziii/piston-meta-kt.git")
                    url.set("https://github.com/mooziii/piston-meta-kt/tree/main")
                }
            }
        }
    }
}
