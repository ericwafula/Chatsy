package helpers

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType,
) {
    commonExtension.run {
        buildFeatures { buildConfig = true }
        val baseUrl = gradleLocalProperties(rootDir, rootProject.providers).getProperty("BASE_URL")
        when (extensionType) {
            ExtensionType.Application -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        release {
                            configureReleaseBuildType(this@configure, baseUrl = baseUrl)
                            isShrinkResources = true
                        }
                        debug { configureDebugBuildType(baseUrl = baseUrl) }
                    }
                }
            }
            ExtensionType.Library ->
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        release { configureReleaseBuildType(this@configure, baseUrl = baseUrl) }
                        debug { configureDebugBuildType(baseUrl = baseUrl) }
                    }
                }
        }
    }
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    baseUrl: String,
) {
    buildConfigField(
        type = "String",
        name = "BASE_URL",
        value = "\"$baseUrl\"",
    )

    isMinifyEnabled = true

    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
    )
}

private fun BuildType.configureDebugBuildType(baseUrl: String) {
    buildConfigField(
        type = "String",
        name = "BASE_URL",
        value = "\"$baseUrl\"",
    )
}
