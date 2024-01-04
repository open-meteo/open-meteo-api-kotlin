plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

group = "com.openmeteo.sdk"
version = "1.0"

kotlin {
    jvm()

    js(IR) {
        nodejs()
        browser {
            testTask {
                useKarma {
                    useChromiumHeadless()
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    linuxX64 {
        binaries.staticLib {
            baseName = "shared"
        }
    }


    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.properties)
            implementation(libs.kotlinx.serialization.protobuf)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }

        nativeMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }

        nativeTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        linuxMain.dependencies {
            implementation(libs.ktor.client.curl)
        }

    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

}
