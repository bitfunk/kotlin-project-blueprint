/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.byEnv
import eu.upwolf.gradle.blueprint.dependency.byProperty
import eu.upwolf.gradle.blueprint.dependency.Dependency

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
// To make it available as direct dependency
group = "eu.upwolf.gradle.blueprint.tools"
version = "1.0.0-SNAPSHOT"

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

dependencies {
    implementation(Dependency.GradlePlugin.projectDependency)

    // Publish
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:_")
    implementation("org.jetbrains.dokka:dokka-core:_")

    // Quality
    // -> Spotless
    implementation("com.diffplug.spotless:spotless-plugin-gradle:_")
    implementation("com.pinterest:ktlint:_")
    // -> Detekt
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:_")

    // Version
    implementation(Dependency.GradlePlugin.gitVersion)
}
