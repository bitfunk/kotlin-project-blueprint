/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    includeBuild("gradlePlugin/blueprint-dependency")
    includeBuild("gradlePlugin/blueprint-configuration")
    includeBuild("gradlePlugin/blueprint-tools")
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Gradle Plugin

// App
include(
    ":app-android",
    ":app-desktop",
    ":app-ios",
    ":app-web",
)

// Common
include(
    ":common:ui:foundation",
    ":common:ui:system",
    ":common:ui:theme",
    ":common:database",
)

// Feature
include(
    ":feature:home:component",
    ":feature:home:ui",
    ":feature:root:component",
    ":feature:root:resources",
    ":feature:root:ui",
    ":feature:splash:component",
    ":feature:splash:resources",
    ":feature:splash:ui",
)

rootProject.name = "MobileProjectBlueprint"
