/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    id("eu.upwolf.gradle.blueprint.dependency")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation(eu.upwolf.gradle.blueprint.dependency.PluginDependency.kotlin)
    implementation(eu.upwolf.gradle.blueprint.dependency.PluginDependency.kotlinSerialization)
    implementation(eu.upwolf.gradle.blueprint.dependency.PluginDependency.android)

    implementation("org.jetbrains.gradle.apple:applePlugin:212.4638.14-0.13.1")

    implementation("eu.upwolf.gradle.blueprint.dependency:blueprint-dependency:1.0.0-SNAPSHOT")
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
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.base") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.base"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.base.KmpConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.feature") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.feature"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.feature.FeatureConfigurationPlugin"
    }
}
