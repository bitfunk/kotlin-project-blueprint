/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

object PluginDependency {
    const val blueprintDependency =
        "eu.upwolf.gradle.blueprint.dependency:blueprint-dependency:1.0.0-SNAPSHOT"

    // Android
    const val android = "com.android.tools.build:gradle:${Version.GradlePlugin.android}"

    const val playPublisher =
        "com.github.triplet.gradle:play-publisher:${Version.GradlePlugin.playPublisher}"

    // Versions
    const val gitVersion =
        "eu.upwolf.gradle.gitversion:gradle-git-version:${Version.GradlePlugin.gitVersion}"

    const val gradleVersions =
        "com.github.ben-manes:gradle-versions-plugin:${Version.GradlePlugin.gradleVersions}"

    // Quality
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.GradlePlugin.detekt}"
}
