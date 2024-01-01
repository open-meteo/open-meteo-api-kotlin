plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()
    js(IR) {
        browser()
        nodejs()
    }
    linuxX64()

    sourceSets {
        /* Main source sets */
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.properties)
                implementation(libs.kotlinx.serialization.protobuf)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.client.core)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
                implementation(npm("@js-joda/timezone", "2.3.0"))
            }
        }
        val linuxMain by creating {
            dependencies {
                implementation(libs.ktor.client.curl)
            }
        }
        val linuxX64Main by getting

        /* Main hierarchy */
        jvmMain.dependsOn(commonMain)
        jsMain.dependsOn(commonMain)
        linuxMain.dependsOn(commonMain)
        linuxX64Main.dependsOn(linuxMain)

        /* Test source sets */
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmTest by getting
        val jsTest by getting
        val linuxTest by creating
        val linuxX64Test by getting

        /* Test hierarchy */
        jvmTest.dependsOn(commonTest)
        jsTest.dependsOn(commonTest)
        linuxTest.dependsOn(commonTest)
        linuxX64Test.dependsOn(linuxTest)
    }
}

android {
    namespace = "com.openmeteo.sdk"
    compileSdk = 31
    defaultConfig {
        minSdk = 21
    }
}
