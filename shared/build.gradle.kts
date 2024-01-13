@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripitacaandroid.android.library")
    id("tripitacaandroid.android.hilt")
}

android {
    namespace = "com.danielwaiguru.tripitacaandroid.shared"
}
dependencies {
    implementation(libs.datastore.preferences.core)
}