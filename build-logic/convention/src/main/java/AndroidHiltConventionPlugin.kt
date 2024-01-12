
@file:Suppress("UnstableApiUsage")

import com.danielwaiguru.tripicaandroid.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
                "ksp"(libs.findLibrary("hilt.ext.compiler").get())
                "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
            }

        }
    }

}