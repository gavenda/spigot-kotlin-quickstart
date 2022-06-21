import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.accessors.dm.LibrariesForMcLibs
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.language.jvm.tasks.ProcessResources

/**
 * Configures the project as a Minecraft plugin.
 */
fun Project.minecraftPlugin(name: String) {
    val libs = the<LibrariesForLibs>()
    val mcLibs = the<LibrariesForMcLibs>()

    tasks.register<Copy>("copyLicense") {
        val srcDir = file("$rootDir/LICENSE")
        val distDir = file("$buildDir/resources/main/META-INF/")

        from(srcDir)
        into(distDir)
    }

    tasks.named("processResources", ProcessResources::class) {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }

    tasks.named("shadowJar", Jar::class) {
        archiveFileName.set("$name.${archiveExtension.get()}")

        val sys = System.getProperties().toMap()
        val jdk = "${sys["java.version"]} ${sys["java.vendor"]} ${sys["java.vm.version"]}"
        val os = "${sys["os.name"]} ${sys["os.arch"]} ${sys["os.version"]}"

        manifest {
            attributes(
                "Built-By" to sys["user.name"],
                "Created-By" to "Gradle ${gradle.gradleVersion}",
                "Build-System" to os,
                "Build-Jdk" to jdk,
                //"Kotlin-Version" to libs.versions.kotlin.asProvider().get(),
                "Implementation-Version" to project.version,
                "Implementation-Title" to name,
                "Implementation-Vendor" to sys["user.name"],
                //"Paper-Version" to mcLibs.versions.paper.asProvider().get()
            )
        }

        mustRunAfter("copyLicense")
    }

    tasks.named("build") {
        dependsOn("copyLicense")
    }
}