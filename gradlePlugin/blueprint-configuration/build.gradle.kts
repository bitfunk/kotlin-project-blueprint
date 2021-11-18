/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.Dependency
import eu.upwolf.gradle.blueprint.dependency.PluginDependency

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    id("eu.upwolf.gradle.blueprint.dependency")
}

// To make it available as direct dependency
group = "eu.upwolf.gradle.blueprint.configuration"
version = "1.0.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

gradlePlugin {
    // Android
    plugins.register("eu.upwolf.gradle.blueprint.configuration.android.app") {
        id = "eu.upwolf.gradle.blueprint.configuration.android.app"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.android.app.AndroidAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.android.library") {
        id = "eu.upwolf.gradle.blueprint.configuration.android.library"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.android.library.AndroidLibraryConfigurationPlugin"
    }

    // iOS
    plugins.register("eu.upwolf.gradle.blueprint.configuration.ios.app") {
        id = "eu.upwolf.gradle.blueprint.configuration.ios.app"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.ios.app.IosAppConfigurationPlugin"
    }

    // Kotlin Multiplatform
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.common") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.common"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.common.CommonConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.feature") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.feature"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.feature.FeatureConfigurationPlugin"
    }
}

dependencies {
    implementation(Dependency.GradlePlugin.projectDependency)

    implementation(Dependency.Kotlin.gradlePlugin)
    implementation(Dependency.Kotlin.Serialization.gradlePlugin)
    implementation(PluginDependency.android)

    implementation("org.jetbrains.gradle.apple:applePlugin:_")

}
