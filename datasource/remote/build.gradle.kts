plugins {
    alias(libs.plugins.chatsy.remote.data)
}

android {
    namespace = "com.example.datasource.remote"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data)
}