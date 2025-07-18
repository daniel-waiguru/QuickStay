plugins {
    id("tripitacaandroid.android.library")
    id("tripitacaandroid.android.hilt")
}

android {
    namespace = "com.danielwaiguru.datastore.lib"
}

dependencies {
    implementation(projects.platform.datastore.contract)
    implementation(libs.datastore.preferences)
}