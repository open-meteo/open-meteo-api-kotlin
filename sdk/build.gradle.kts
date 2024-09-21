plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    jvm()
    js {
        nodejs()
        browser()
        binaries.executable()
    }

    linuxX64 {
        binaries.staticLib {
            baseName = "sdk"
        }
    }

    mingwX64 {
        binaries.staticLib {
            baseName = "sdk"
        }
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