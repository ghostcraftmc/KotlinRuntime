plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("net.minecrell.plugin-yml.bungee") version "0.5.2"
}

group = "me.abhigya"
version = "1.0"

val javaVersion = 1.8

val kotlinVersion = "1.8.21"
val kotlinxCoroutinesVersion = "1.7.1"
val kotlinxSerializationVersion = "1.4.1"
val kotlinDateTimeVersion = "0.4.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.12.1-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")

    implementation(kotlin("stdlib-jdk8"))

    implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:$kotlinxCoroutinesVersion"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-properties:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$kotlinxSerializationVersion")
    implementation("com.charleskorn.kaml:kaml:0.54.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDateTimeVersion")
    implementation("org.jetbrains:annotations:23.0.0")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveFileName.set("KotlinLibrary-$kotlinVersion.jar")
        exclude("com/google/errorprone/annotations/**")
        exclude("DebugProbesKt.bin")
        exclude("META-INF/**")
    }

    compileKotlin {
        kotlinOptions.suppressWarnings = true
        kotlinOptions.jvmTarget = javaVersion.toString()
        kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    jar {
        enabled = false
    }
}

bukkit {
    main = "me.abhigya.kotlinlibrary.SpigotPlugin"
    name = "KotlinLibrary"
    version = kotlinVersion
    apiVersion = "1.13"
    author = "Abhigya"
}

bungee {
    main = "me.abhigya.kotlinlibrary.BungeePlugin"
    name = "KotlinLibrary"
    version = kotlinVersion
    author = "Abhigya"
}