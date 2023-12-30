rootProject.name = "open-meteo-kmp-sdk"
include(":shared")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("multiplatform").version("1.9.22")
        kotlin("plugin.serialization").version("1.9.22")
        id("com.android.library").version("8.2.0")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            library("kotlinx-serialization-properties", "org.jetbrains.kotlinx:kotlinx-serialization-properties:1.6.2")
            library("kotlinx-serialization-protobuf", "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.2")
            library("kotlinx-serialization-json", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
            library("kotlinx-datetime", "org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
            library("ktor-client-okhttp", "io.ktor:ktor-client-okhttp:2.3.7")
            library("ktor-client-darwin", "io.ktor:ktor-client-darwin:2.3.7")
            library("ktor-client-js", "io.ktor:ktor-client-js:2.3.7")
            library("ktor-client-core", "io.ktor:ktor-client-core:2.3.7")
        }
    }
}
