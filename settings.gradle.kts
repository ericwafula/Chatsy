import java.util.Properties
import java.io.File
import kotlin.toString

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            val props = Properties().apply {
                val f = rootDir.resolve("local.properties")
                if (f.exists()) f.inputStream().use { load(it) }
            }

            url = uri("https://maven.pkg.github.com/bizilabs/halo")
            credentials {
                username = props["github.username"]?.toString() ?: ""
                password = props["github.token"]?.toString() ?: ""
            }
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Chatsy"
include(":app")
include(":presentation:designsystem")
include(":presentation:ui")
include(":presentation:auth")
include(":presentation:chat")
include(":domain")
include(":data")
include(":datasource:local")
include(":datasource:remote")
