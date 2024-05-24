
import com.android.build.gradle.LibraryExtension
import com.danielwaiguru.tripicaandroid.convention.configureAndroidCompose
import com.danielwaiguru.tripicaandroid.convention.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply(getPluginId("compose-compiler"))
            }
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }

}