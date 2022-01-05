/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    alias(libs.plugins.gradleBlueprintDependency)
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
    // Apps
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.android") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.android"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.app.android.AndroidAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.desktop") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.desktop"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.app.desktop.DesktopAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.ios") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.ios"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.app.ios.IosAppConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.app.web") {
        id = "eu.upwolf.gradle.blueprint.configuration.app.web"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.app.web.WebAppConfigurationPlugin"
    }

    // Android

    plugins.register("eu.upwolf.gradle.blueprint.configuration.android.library") {
        id = "eu.upwolf.gradle.blueprint.configuration.android.library"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.android.library.AndroidLibraryConfigurationPlugin"
    }


    // KMP - Common
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.common") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.common"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.common.CommonConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.compose") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.compose"
        implementationClass =
            "eu.upwolf.gradle.blueprint.configuration.kmp.compose.ComposeConfigurationPlugin"
    }
    // KMP - Feature
    plugins.register("eu.upwolf.gradle.blueprint.configuration.kmp.feature") {
        id = "eu.upwolf.gradle.blueprint.configuration.kmp.feature"
        implementationClass = "eu.upwolf.gradle.blueprint.configuration.kmp.feature.FeatureConfigurationPlugin"
    }
}

dependencies {
    implementation(libs.gradleBlueprintDependencyPlugin)

    implementation(libs.gradleAndroidPlugin)

    implementation(libs.gradleKotlinPlugin)
    implementation(libs.gradleKotlinSerializationPlugin)

    implementation(libs.gradleJetbrainsComposePlugin)

    implementation(libs.gradleJetbrainsApplePlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.3.3"
    distributionType = Wrapper.DistributionType.ALL
}
