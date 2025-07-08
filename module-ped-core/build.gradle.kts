plugins {
    `maven-publish`

    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
        optIn.add("kotlin.time.ExperimentalTime")
    }
    jvm()
    js(IR) {
        browser {
            binaries.library()
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(libs.kotlinx.serialization.json)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
