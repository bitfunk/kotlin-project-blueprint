/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal fun Project.androidApp(action: Action<BaseAppModuleExtension>) {
    extensions.configure(BaseAppModuleExtension::class.java, action)
}

internal fun Project.androidLibrary(action: Action<LibraryExtension>) {
    extensions.configure(LibraryExtension::class.java, action)
}

internal fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

internal fun DependencyHandler.`testImplementation`(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

internal fun NamedDomainObjectContainerScope<KotlinSourceSet>.fixAndroidSourceSets(
    androidTest: KotlinSourceSet
) {
    val androidTestFixtures = maybeCreate("androidTestFixtures")

    androidTest.dependsOn(androidTestFixtures)
}
