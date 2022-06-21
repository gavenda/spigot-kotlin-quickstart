rootProject.name = "quik"

include("quik")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        maven(url = "https://papermc.io/repo/repository/maven-public") {
            name = "paper-snapshots"
        }
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots") {
            name = "spigot-snapshots"
        }
    }

    versionCatalogs {
        create("mcLibs") {
            from(files("gradle/mcLibs.versions.toml"))
        }
    }
}