plugins {
    alias(libs.plugins.chatsy.android.library.compose)
}

android {
    namespace = "com.example.presentation.chat"
}

dependencies {
    implementation(projects.presentation.designsystem)
    implementation(projects.presentation.ui)
    implementation(libs.bundles.koin.compose)
}