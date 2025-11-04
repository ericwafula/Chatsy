plugins {
    alias(libs.plugins.chatsy.android.library)
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.bundles.koin)
}
