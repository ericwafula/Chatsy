import com.android.build.api.dsl.ApplicationExtension
import helpers.ExtensionType
import helpers.configureBuildTypes
import helpers.configureCompose
import helpers.configureKotlin
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("chatsy.compose")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlin(this)
                configureBuildTypes(ExtensionType.Application)
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.core.ktx").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                "implementation"(libs.findLibrary("androidx.activity.compose").get())
                "implementation"(libs.findBundle("koin.compose").get())
                "implementation"(libs.findLibrary("androidx.navigation3.runtime").get())
                "implementation"(libs.findLibrary("androidx.navigation3.ui").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.navigation3").get())
            }
        }
    }
}