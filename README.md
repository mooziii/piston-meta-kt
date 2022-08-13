# piston-meta-kt

Kotlin library to interact with mojangs [launchermeta](https://launchermeta.mojang.com/mc/game/version_manifest_v2.json) and "[piston-data](https://piston-meta.mojang.com/v1/packages/68cded4616fba9fbefb3f895033c261126c5f89c/1.19.2.json)" api

### Add the dependency

**Gradle (Kotlin)**

```kotlin
implementation("me.obsilabor:piston-meta-kt:1.0.0")
```

**Gradle (Groovy)**

```groovy
implementation 'me.obsilabor:piston-meta-kt:1.0.0'
```

**Maven**

```xml
<dependency>
    <groupId>me.obsilabor</groupId>
    <artifactId>piston-meta-kt</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Usage

```kotlin
suspend fun main() {
    val versions = PistonMetaClient.getLauncherMeta().versions
    for (version in versions) {
        println(version > MinecraftVersions.OLD_ALPHA_A1_0_17_02) // Compare versions!
    }
}
```

