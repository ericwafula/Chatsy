import helpers.configureKotlinJvmOptions
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinJvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.jvm")
            }

            configureKotlinJvmOptions()

            dependencies {
                "implementation"(libs.findLibrary("kotlinx.coroutines.core").get())
            }
        }
    }
}