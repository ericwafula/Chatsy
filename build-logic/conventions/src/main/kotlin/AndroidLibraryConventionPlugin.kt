import com.android.build.gradle.LibraryExtension
import helpers.ExtensionType
import helpers.configureBuildTypes
import helpers.configureKotlin
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                configureKotlin(this)
                configureBuildTypes(ExtensionType.Library)

                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.core.ktx").get())
                "implementation"(libs.findLibrary("kotlinx.coroutines.core").get())
                "testImplementation"(libs.findLibrary("junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.espresso.core").get())
            }
        }
    }
}