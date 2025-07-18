
import com.danielwaiguru.tripicaandroid.convention.configureKotlinJvm
import com.danielwaiguru.tripicaandroid.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply(libs.findPlugin("kotlinx-serialization").get().get().pluginId)
            }
            configureKotlinJvm()
        }
    }
}