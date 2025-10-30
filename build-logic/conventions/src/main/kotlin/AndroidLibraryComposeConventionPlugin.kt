import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("chatsy.android.library")
                apply("chatsy.compose")
            }

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }
            }
        }
    }
}