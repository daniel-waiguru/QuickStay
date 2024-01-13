

package com.danielwaiguru.tripicaandroid.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog =  extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.getIntVersion(name: String): Int =
    libs.findVersion(name).get().toString().toInt()