@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripicaandroid.android.library")
    id("tripicaandroid.android.library.compose")
}

android {
    namespace = "com.danielwaiguru.tripicaandroid.designsystem"
}

dependencies {
    implementation(libs.google.fonts)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.shimmer)
}