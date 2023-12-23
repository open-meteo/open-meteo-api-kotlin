plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    `maven-publish`
}

version = "0.7.1-beta.1"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-properties:1.6.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            groupId = "com.open-meteo"
            artifactId = "open-meteo-api-kotlin"
        }
    }
}
