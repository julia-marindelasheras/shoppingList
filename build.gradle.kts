val kotlinVersion = "1.5.20"
val serializationVersion = "1.2.1"
val ktorVersion = "1.6.1"
val logbackVersion = "1.2.3"
val kmongoVersion = "4.2.7"
val reactWrappersVersion = "17.0.2-pre.214-kotlin-1.5.20"

plugins {
    kotlin("multiplatform") version "1.5.20"
    application //to run JVM part
    kotlin("plugin.serialization") version "1.5.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}

kotlin {
    jvm {
        withJava()
    }
    js {
        browser {
            binaries.executable()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
                implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kmongoVersion")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:$reactWrappersVersion")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:$reactWrappersVersion")
            }
        }
    }

    application {
        mainClass.set("ServerKt")
    }
}