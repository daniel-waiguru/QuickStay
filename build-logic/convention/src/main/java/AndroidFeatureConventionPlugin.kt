
import com.android.build.gradle.LibraryExtension
import com.danielwaiguru.tripicaandroid.convention.getPluginId
import com.danielwaiguru.tripicaandroid.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("tripitacaandroid.android.library")
                apply("tripitacaandroid.android.hilt")
                apply(getPluginId("kotlinx-serialization"))
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add("implementation", project(":designsystem"))
                add("implementation", project(":shared"))
                add("implementation", libs.findLibrary("kotlinx-json").get())
                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}