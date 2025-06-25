@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripitacaandroid.android.feature")
    id("tripitacaandroid.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.tripitacaandroid.booking.lib"
}
dependencies {
    implementation(projects.shared)
    implementation(projects.features.properties.contract)
}

