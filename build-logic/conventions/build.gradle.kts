plugins {
    `kotlin-dsl`
}

group = "com.ericwathome.buildLogic"

gradlePlugin {
    plugins {
        fun createPlugin(name: String, id: String, implementationClass: String) {
            create(name) {
                this.id = id
                this.implementationClass = implementationClass
            }
        }

        createPlugin(
            name = "androidApplication",
            id = "chatsy.android.application",
            implementationClass = "AndroidApplicationConventionPlugin"
        )

        createPlugin(
            name = "compose",
            id = "chatsy.compose",
            implementationClass = "ComposeConventionPlugin"
        )

        createPlugin(
            name = "androidLibrary",
            id = "chatsy.android.library",
            implementationClass = "AndroidLibraryConventionPlugin"
        )

        createPlugin(
            name = "androidLibraryCompose",
            id = "chatsy.android.library.compose",
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        )

        createPlugin(
            name = "kotlinJvm",
            id = "chatsy.kotlin.jvm",
            implementationClass = "KotlinJvmConventionPlugin"
        )

        createPlugin(
            name = "androidLocalData",
            id = "chatsy.local.data",
            implementationClass = "AndroidLocalDataConventionPlugin"
        )

        createPlugin(
            name = "androidRemoteData",
            id = "chatsy.remote.data",
            implementationClass = "AndroidRemoteDataConventionPlugin"
        )
    }
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kgp)
    compileOnly(libs.rgp)
}