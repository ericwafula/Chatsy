plugins {
    alias(libs.plugins.chatsy.android.library.compose)
}

android {
    namespace = "com.example.presentation.auth"
}

dependencies {
    implementation(projects.presentation.designsystem)
    implementation(projects.presentation.ui)
    implementation(libs.bundles.koin.compose)
}