plugins {
    id("quickstay.jvm.library")
}

dependencies {
    // Exposed to consumers' test classpaths so test rules/utilities compile and run.
    api(libs.junit4)
    api(libs.coroutines.test)
}