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
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":ped-core"))

                implementation(kotlin("stdlib"))
                implementation(libs.kotlinx.serialization.json)

                implementation(libs.bsonkt)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.mongodb.sync)
                implementation(libs.mongodb.reactivestreams)
            }
        }
    }
}
