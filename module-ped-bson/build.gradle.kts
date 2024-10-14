plugins {
    `maven-publish`

    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":ped-core"))

                implementation(kotlin("stdlib"))
                implementation(libs.kotlin.serialization.json)
                implementation(libs.kotlin.datetime)

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
