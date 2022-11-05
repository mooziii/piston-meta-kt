# piston-meta-kt

Kotlin library to interact with mojangs [launchermeta](https://launchermeta.mojang.com/mc/game/version_manifest_v2.json) and "[piston-meta](https://piston-meta.mojang.com/v1/packages/68cded4616fba9fbefb3f895033c261126c5f89c/1.19.2.json)" api as well as authenticating with Minecraft via Microsoft-Auth

### Add the dependency

**Gradle (Kotlin)**

```kotlin
implementation("me.obsilabor:piston-meta-kt:1.0.6")
```

**Gradle (Groovy)**

```groovy
implementation 'me.obsilabor:piston-meta-kt:1.0.6'
```

**Maven**

```xml
<dependency>
    <groupId>me.obsilabor</groupId>
    <artifactId>piston-meta-kt</artifactId>
    <version>1.0.6</version>
</dependency>
```

## Usage

```kotlin
suspend fun main() {
    val versions = PistonMetaClient.getLauncherMeta().versions
    for (version in versions) {
        println(version > MinecraftVersions.OLD_ALPHA_A1_0_17_02) // Compare versions!
    }
}
```

### Authenticate with minecraft accounts

```kotlin
val msauth = MicrosoftAuth("URL_PREFIX", "AZURE_CLIENT_ID", "AZURE_CLIENT_SECRET", ssl = true) {
    println(it.id) // specific auth id
    println(it.token.user) // xbox user id
    println(it.token.value) // minecraft access token
    println(it.minecraftUUID) // minecraft uuid
    println(it.minecraftName) // minecraft name 
}
println(msauth.generateURI("specific_auth_id"))
msauth.setup(PORT) // Blocks the thread until the ktor server stops for whatever reason
```

You have to register an **Azure App** and add the following as **Web Redirect URI**: `https://<your-domain>/msauth/done` (SSL is required if you aren't using localhost!)
Note: `URL_PREFIX` is your domain without the `https://` (If you're using localhost you have to specify the port here.)

For a deeper understanding of whats happening you may take a look at [this documentation](https://mojang-api-docs.netlify.app/authentication/index.html)
