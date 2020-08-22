import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    base
    kotlin("jvm") version Version.KOTLIN
}

allprojects {
    group = "work.gavenda.quik"
    version = "1.0.0"

    repositories {
        jcenter()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

subprojects {
    ext {
        set("kotlinVersion", Version.KOTLIN)
    }

    // Copy all artifacts to root build/libs
    val copyArtifacts = tasks.register<Copy>("copyArtifacts") {
        val distDir = file("$rootDir/build/libs")
        val srcDir = file("$buildDir/libs")

        from(srcDir)
        into(distDir)
    }

    afterEvaluate {
        tasks.build {
            finalizedBy(copyArtifacts)
        }
    }
}
