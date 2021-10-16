/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.byEnv
import eu.upwolf.gradle.blueprint.dependency.byProperty

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    id("eu.upwolf.gradle.blueprint.dependency")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven {
        url = uri("https://maven.pkg.github.com/wmontwe/gradle-git-version")
        credentials {
            username = "githubPackageDownload.user".byProperty(project) ?: "GITHUB_PACKAGE_DOWNLOAD_USER".byEnv()
            password = "githubPackageDownload.key".byProperty(project) ?: "GITHUB_PACKAGE_DOWNLOAD_KEY".byEnv()
        }
    }
}

dependencies {
    // implementation(eu.upwolf.gradle.blueprint.dependency.PluginDependency.kotlin)
    // implementation(eu.upwolf.gradle.blueprint.dependency.PluginDependency.android)

    implementation("eu.upwolf.gradle.blueprint.dependency:blueprint-dependency:1.0.0-SNAPSHOT")

    // Publish
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.5.0")
    implementation("org.jetbrains.dokka:dokka-core:1.5.0")

    // Quality
    // -> Spotless
    implementation("com.diffplug.spotless:spotless-plugin-gradle:5.14.3")
    implementation("com.pinterest:ktlint:0.42.1")
    // -> Detekt
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.1")

    // Version
    implementation("eu.upwolf.gradle.gitversion:gradle-git-version:0.12.6")
}

gradlePlugin {
    // Publish
    plugins.register("eu.upwolf.gradle.blueprint.publish") {
        id = "eu.upwolf.gradle.blueprint.publish"
        implementationClass = "eu.upwolf.gradle.blueprint.publish.PublishConfigurationPlugin"
    }
    // Quality
    plugins.register("eu.upwolf.gradle.blueprint.quality.spotless") {
        id = "eu.upwolf.gradle.blueprint.quality.spotless"
        implementationClass = "eu.upwolf.gradle.blueprint.quality.spotless.SpotlessConfigurationPlugin"
    }
    plugins.register("eu.upwolf.gradle.blueprint.quality.detekt") {
        id = "eu.upwolf.gradle.blueprint.quality.detekt"
        implementationClass = "eu.upwolf.gradle.blueprint.quality.detekt.DetektConfigurationPlugin"
    }
    // Version
    plugins.register("eu.upwolf.gradle.blueprint.version") {
        id = "eu.upwolf.gradle.blueprint.version"
        implementationClass = "eu.upwolf.gradle.blueprint.version.VersionConfigurationPlugin"
    }
}
