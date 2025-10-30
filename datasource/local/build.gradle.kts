plugins {
    alias(libs.plugins.chatsy.local.data)
}

android {
    namespace = "com.example.datasource.local"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data)
}