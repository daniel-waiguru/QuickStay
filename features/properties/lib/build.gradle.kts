@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripitacaandroid.android.feature")
    id("tripitacaandroid.android.library.compose")
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.danielwaiguru.tripitacaandroid.properties.lib"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.features.properties.contract)
    implementation(projects.features.auth.contract)
    implementation(libs.maps.compose)
    implementation(libs.play.services.location)
}