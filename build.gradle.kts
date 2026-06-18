// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.gradle.testing.jacoco.tasks.JacocoReport

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.compose.compiler) apply false
    jacoco
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

// Generated / non-unit-testable code excluded so the % reflects real logic.
val coverageExclusions = listOf(
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*_Factory*.*",
    "**/*_HiltModules*.*",
    "**/Hilt_*.*",
    "**/*_GeneratedInjector*.*",
    "**/hilt_aggregated_deps/**",
    "**/dagger/hilt/**",
    "**/*ComposableSingletons*.*",
    "**/*Screen*.*",
    "**/PropertyItem*.*", // Compose list item
    "**/di/**",
    "**/navigation/**",
    "**/components/**",
    "**/presentation/utils/**",
    "**/data/models/**",
    "**/*UIState*.*",
    "**/*UiState*.*",
    "**/*Event*.*",
    // Android-IO code exercised only by Robolectric tests; JaCoCo's on-the-fly agent
    // cannot instrument classes loaded inside Robolectric's sandbox classloader, so it
    // would report 0% despite being covered. Excluded to keep the metric honest.
    "**/data/utils/**",
    "**/data/repositories/PropertiesRepositoryImpl*",
)

// Aggregate JaCoCo coverage across every tested module into a single report at the root.
// `./gradlew jacocoAggregatedReport` -> build/reports/jacoco/aggregated/report.xml (uploaded to Codecov in CI).
//
// Modules are discovered automatically: any subproject that applies the `jacoco` plugin
// (every Android library via AndroidLibraryConventionPlugin) and has a `src/test` source set
// is included. Adding a new module with unit tests requires no change here.
tasks.register<JacocoReport>("jacocoAggregatedReport") {
    group = "verification"
    description = "Merges debug unit-test coverage from all modules into a single report."

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
        xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/aggregated/report.xml"))
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/aggregated/html"))
    }
}

gradle.projectsEvaluated {
    val coveredProjects = subprojects.filter { sub ->
        sub.plugins.hasPlugin("jacoco") && sub.projectDir.resolve("src/test").isDirectory
    }

    tasks.named<JacocoReport>("jacocoAggregatedReport") {
        dependsOn(coveredProjects.map { "${it.path}:testDebugUnitTest" })

        classDirectories.from(
            coveredProjects.flatMap { module ->
                // AGP 9 emits Kotlin classes under built_in_kotlinc and Java under javac.
                listOf(
                    "intermediates/built_in_kotlinc/debug/compileDebugKotlin/classes",
                    "intermediates/javac/debug/compileDebugJavaWithJavac/classes",
                ).map { relativePath ->
                    fileTree(module.layout.buildDirectory.dir(relativePath).get().asFile) {
                        setExcludes(coverageExclusions)
                    }
                }
            },
        )
        sourceDirectories.from(
            coveredProjects.map { it.layout.projectDirectory.dir("src/main/java").asFile },
        )
        executionData.from(
            coveredProjects.map { module ->
                fileTree(module.layout.buildDirectory.get().asFile) {
                    setIncludes(
                        listOf(
                            "outputs/unit_test_code_coverage/debugUnitTest/*.exec",
                            "jacoco/*.exec",
                        ),
                    )
                }
            },
        )
    }
}