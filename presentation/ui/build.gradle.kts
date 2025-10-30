plugins {
    alias(libs.plugins.chatsy.android.library.compose)
}

android {
    namespace = "com.example.presentation.ui"
}

dependencies {
    api(projects.domain)
    implementation(projects.presentation.designsystem)
}