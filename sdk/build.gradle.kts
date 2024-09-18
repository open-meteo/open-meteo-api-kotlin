plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    jvm()
    js {
        nodejs()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":flatbuffers"))
        }
    }

}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().download = false
    // "true" for default behavior
}