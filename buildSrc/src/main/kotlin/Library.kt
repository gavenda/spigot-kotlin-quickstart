object Version {
    const val KOTLIN = "1.4.0"
    const val SPIGOT = "1.16.2-R0.1-SNAPSHOT"
    const val SQLITE = "3.32.3"
    const val HIKARICP = "3.4.5"
}

object Library {
    const val SPIGOT = "org.spigotmc:spigot-api:${Version.SPIGOT}"
    const val SQLITE = "org.xerial:sqlite-jdbc:${Version.SQLITE}"
    const val HIKARICP = "com.zaxxer:HikariCP:${Version.HIKARICP}"
}