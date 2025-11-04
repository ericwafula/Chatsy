plugins {
    alias(libs.plugins.chatsy.android.library.compose)
}

android {
    namespace = "com.example.presentation.designsystem"
}

dependencies {
    api(libs.halo)
    api(libs.compose.material.icons.ext)
}
