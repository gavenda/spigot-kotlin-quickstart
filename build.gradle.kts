import java.io.ByteArrayOutputStream

plugins {
    base
    alias(libs.plugins.kotlin.jvm)
}

val gitHash: String = ByteArrayOutputStream().use { outputStream ->
    project.exec {
        commandLine("git")
        args("rev-parse", "--short", "HEAD")
        standardOutput = outputStream
    }
    outputStream.toString().trim()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    group = "work.gavenda.quik"
    version = "1.0.0-SNAPSHOT"
}

tasks.register("version") {
    group = "help"
    description = "Prints project version."

    doLast {
        println(project.version)
    }
}