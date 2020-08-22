import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.language.jvm.tasks.ProcessResources

/**
 * Configures the current project as a Spigot plugin.
 */
fun Project.spigotPlugin(archivesBaseName: String, excludeKotlin: Boolean = true) {

    val processResources by tasks.existing(ProcessResources::class)
    val shadowJar by tasks.existing(ShadowJar::class)
    val jar by tasks.existing(Jar::class)
    val build = tasks.named("build")

    dependencies {
        "implementation"(kotlin("stdlib-jdk8"))
        // Spigot
        "implementation"(Library.SPIGOT)
    }

    jar {
        archiveBaseName.set(archivesBaseName)
    }

     processResources {
         filesMatching("plugin.yml") {
             expand(project.properties)
         }
     }

    shadowJar {
        // We don't want a suffix
        archiveClassifier.set("")

        dependencies {
            if (excludeKotlin) {
                // Remove Kotlin
                exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:.*"))
                exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk7:.*"))
                exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib:.*"))
                exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-common:.*"))
                exclude(dependency("org.jetbrains:annotations:.*"))
            }

            // Remove Spigot included dependencies
            exclude(dependency("commons-lang:commons-lang:.*"))
            exclude(dependency("com.google.guava:guava:.*"))
            exclude(dependency("com.google.code.gson:gson:.*"))
            exclude(dependency("net.md-5:bungeecord-chat:.*"))
            exclude(dependency("org.yaml:snakeyaml:.*"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
