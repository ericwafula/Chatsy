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
        val wsConnectionUrl = gradleLocalProperties(rootDir, rootProject.providers).getProperty("WS_CONNECTION_URL")
        when (extensionType) {
            ExtensionType.Application -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        release {
                            configureReleaseBuildType(this@configure, baseUrl = baseUrl, wsConnectionUrl = wsConnectionUrl)
                            isShrinkResources = true
                        }
                        debug { configureDebugBuildType(baseUrl = baseUrl, wsConnectionUrl = wsConnectionUrl) }
                    }
                }
            }
            ExtensionType.Library ->
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        release { configureReleaseBuildType(this@configure, baseUrl = baseUrl, wsConnectionUrl = wsConnectionUrl) }
                        debug { configureDebugBuildType(baseUrl = baseUrl, wsConnectionUrl = wsConnectionUrl) }
                    }
                }
        }
    }
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    baseUrl: String,
    wsConnectionUrl: String,
) {
    buildConfigField(
        type = "String",
        name = "BASE_URL",
        value = "\"$baseUrl\"",
    )

    buildConfigField(
        type = "String",
        name = "WS_CONNECTION_URL",
        value = "\"$wsConnectionUrl\"",
    )

    isMinifyEnabled = true

    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
    )
}

private fun BuildType.configureDebugBuildType(
    baseUrl: String,
    wsConnectionUrl: String,
) {
    buildConfigField(
        type = "String",
        name = "BASE_URL",
        value = "\"$baseUrl\"",
    )

    buildConfigField(
        type = "String",
        name = "WS_CONNECTION_URL",
        value = "\"$wsConnectionUrl\"",
    )
}
