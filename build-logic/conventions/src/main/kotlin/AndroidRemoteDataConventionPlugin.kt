import com.android.build.gradle.LibraryExtension
import helpers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRemoteDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("chatsy.android.library")
            }

            dependencies {
                "implementation"(libs.findBundle("ktor").get())
            }
        }
    }
}
