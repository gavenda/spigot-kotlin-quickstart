plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
}

minecraftPlugin("Quik")

dependencies {
    compileOnly(libs.kotlin.stdlib.jdk8)
    compileOnly(mcLibs.spigot.api)
}
