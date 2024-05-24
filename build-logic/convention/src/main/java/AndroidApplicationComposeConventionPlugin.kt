
import com.android.build.api.dsl.ApplicationExtension
import com.danielwaiguru.tripicaandroid.convention.configureAndroidCompose
import com.danielwaiguru.tripicaandroid.convention.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
                apply(getPluginId("compose-compiler"))
            }
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }

}