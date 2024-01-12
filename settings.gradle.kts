pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TripitacaAndroidTest"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":designsystem")
include(":features:auth:presentation")
include(":features:properties:presentation")
include(":shared")
include(":features:auth:data")
