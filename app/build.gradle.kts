plugins {
    alias(libs.plugins.chatsy.android.application)
}

android {
    namespace = "com.example.chatsy"

    defaultConfig {
        applicationId = "com.example.chatsy"
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.datasource.local)
    implementation(projects.datasource.remote)
    implementation(projects.presentation.designsystem)
    implementation(projects.presentation.ui)
    implementation(projects.presentation.chat)
    implementation(projects.presentation.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}