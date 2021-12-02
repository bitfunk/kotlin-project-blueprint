/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.23.0"
}

refreshVersions {
    versionsPropertiesFile = settings.rootDir.resolve("config/refreshVersions/versions.properties")
}

// Gradle Plugin
includeBuild("gradlePlugin/blueprint-dependency")
includeBuild("gradlePlugin/blueprint-configuration")
includeBuild("gradlePlugin/blueprint-tools")

// App
include(
    ":app-android",
    ":app-desktop",
    ":app-ios",
)

// Common
include(
    ":common:database",
    ":common:theme",
)

// Feature
include(
    ":feature:home",
)

rootProject.name = "MobileProjectBlueprint"
