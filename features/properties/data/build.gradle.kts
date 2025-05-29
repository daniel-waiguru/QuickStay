@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripitacaandroid.android.library")
    id("tripitacaandroid.android.hilt")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.danielwaiguru.tripitacaandroid.properties.data"
}
dependencies {
    implementation(libs.kotlinx.json)
    implementation(projects.shared)
}