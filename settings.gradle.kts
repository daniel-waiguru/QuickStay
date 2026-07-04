pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("com.gradle.develocity") version "4.5.0"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
        termsOfUseAgree = "yes"
        val isCi = System.getenv("CI") != null
        // Only publish scans from CI; keep local builds from sending data externally.
        publishing.onlyIf { isCi }
        if (isCi) {
            tag("CI")
            // Ensure the scan finishes uploading before the runner terminates.
            uploadInBackground = false
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "TripitacaAndroid"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:testing")
include(":designsystem")
include(":features:auth:lib")
include(":features:auth:contract")
include(":features:properties:lib")
include(":features:properties:contract")
include(":features:booking:lib")
include(":features:booking:contract")
include(":platform:datastore:")
include(":platform:datastore:contract")
include(":platform:datastore:lib")
