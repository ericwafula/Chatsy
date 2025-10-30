package helpers

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.configureCompose(project: Project) {
    "implementation"(platform(project.libs.findLibrary("androidx.compose.bom").get()))
    "implementation"(project.libs.findLibrary("androidx.compose.ui").get())
    "implementation"(project.libs.findLibrary("androidx.compose.ui.graphics").get())
    "implementation"(project.libs.findLibrary("androidx.compose.ui.tooling.preview").get())
    "implementation"(project.libs.findLibrary("androidx.compose.material3").get())
    "androidTestImplementation"(platform(project.libs.findLibrary("androidx.compose.bom").get()))
    "androidTestImplementation"(project.libs.findLibrary("androidx.compose.ui.test.junit4").get())
    "debugImplementation"(project.libs.findLibrary("androidx.compose.ui.tooling").get())
    "debugImplementation"(project.libs.findLibrary("androidx.compose.ui.test.manifest").get())
}