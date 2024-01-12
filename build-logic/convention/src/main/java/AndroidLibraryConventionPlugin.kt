
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.danielwaiguru.tripicaandroid.convention.configureKotlinAndroid
import com.danielwaiguru.tripicaandroid.convention.configurePrintApksTask
import com.danielwaiguru.tripicaandroid.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
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
                add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())
                "implementation"(libs.findLibrary("timber").get())
            }
        }
    }
}