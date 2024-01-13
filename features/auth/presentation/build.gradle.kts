@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripitacaandroid.android.feature")
    id("tripitacaandroid.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.tripitacaandroid.auth.presentation"
}

dependencies {
    implementation(libs.play.services.auth)
    implementation(projects.shared)
    implementation(projects.features.auth.data)
}