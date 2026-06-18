
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.danielwaiguru.quickstay.convention.configureKotlinAndroid
import com.danielwaiguru.quickstay.convention.configurePrintApksTask
import com.danielwaiguru.quickstay.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("jacoco")
            }

            extensions.configure<JacocoPluginExtension> {
                toolVersion = libs.findVersion("jacoco").get().toString()
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }
                buildTypes {
                    getByName("debug") {
                        // Generates a JaCoCo .exec file when testDebugUnitTest runs.
                        enableUnitTestCoverage = true
                    }
                }
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        isReturnDefaultValues = true
                    }
                }
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
            configurations.configureEach {
                resolutionStrategy {
                    force(libs.findLibrary("junit4").get())
                    // Temporary workaround for https://issuetracker.google.com/174733673
                    force("org.objenesis:objenesis:2.6")
                }
            }
            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("junit4").get())
                add("testImplementation", libs.findBundle("test").get())
                add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())
                "implementation"(libs.findLibrary("timber").get())
            }
        }
    }
}