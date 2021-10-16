/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

object PluginDependency {
    const val blueprintDependency =
        "eu.upwolf.gradle.blueprint.dependency:blueprint-dependency:1.0.0-SNAPSHOT"

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.GradlePlugin.kotlin}"
    const val kotlinCompilerEmbeddable =
        "org.jetbrains.kotlin:kotlin-compiler-embeddable:${Version.GradlePlugin.kotlin}"
    const val kotlinSerialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Version.GradlePlugin.kotlin}"

    const val sqlDelight =
        "com.squareup.sqldelight:gradle-plugin:${Version.GradlePlugin.sqlDelight}"

    // Android
    const val android = "com.android.tools.build:gradle:${Version.GradlePlugin.android}"

    const val playPublisher =
        "com.github.triplet.gradle:play-publisher:${Version.GradlePlugin.playPublisher}"

    const val sentryAndroid =
        "io.sentry:sentry-android-gradle-plugin:${Version.GradlePlugin.sentryAndroid}"

    // Versions
    const val gitVersion =
        "eu.upwolf.gradle.gitversion:gradle-git-version:${Version.GradlePlugin.gitVersion}"

    const val gradleVersions =
        "com.github.ben-manes:gradle-versions-plugin:${Version.GradlePlugin.gradleVersions}"

    // Quality
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.GradlePlugin.detekt}"
    const val detektFormatting =
        "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.GradlePlugin.detekt}"

    const val gson = "com.google.code.gson:gson:${Version.Jvm.gson}"
}
