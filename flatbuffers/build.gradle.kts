plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    explicitApi()

    jvm()
    js {
        nodejs()
        browser()
        binaries.executable()
    }

    macosX64()
    macosArm64()
    iosArm64()
    iosSimulatorArm64()

    linuxX64 {
        binaries.staticLib {
            baseName = "flatbuffers"
        }
    }

    mingwX64 {
        binaries.staticLib {
            baseName = "flatbuffers"
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }

            kotlin.srcDir("src/commonTest/generated/kotlin/")
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("com.google.flatbuffers:flatbuffers-java:2.0.3")
            }
        }
        val jvmMain by getting {
        }

        val macosX64Main by getting
        val macosArm64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val linuxX64Main by getting
        val mingwX64Main by getting

        all {
            languageSettings.optIn("kotlin.ExperimentalUnsignedTypes")
        }

    }

}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().download = false
    // "true" for default behavior
}
