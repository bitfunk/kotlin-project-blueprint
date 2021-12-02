/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

object PluginDependency {
    // Android
    const val android = "com.android.tools.build:gradle:${Version.GradlePlugin.android}"

    const val playPublisher =
        "com.github.triplet.gradle:play-publisher:${Version.GradlePlugin.playPublisher}"
}
