plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

spigotPlugin("Kotlin", excludeKotlin = false)

version = Version.KOTLIN