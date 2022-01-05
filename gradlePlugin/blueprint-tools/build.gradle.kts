/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.byEnv
import eu.upwolf.gradle.blueprint.dependency.byProperty

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    alias(libs.plugins.gradleBlueprintDependency)
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

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    sourceCompatibility = "11"
    targetCompatibility = "11"

    kotlinOptions {
        jvmTarget = "11"

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=org.jetbrains.compose.ExperimentalComposeLibrary",
        )
    }
}

dependencies {
    implementation(libs.gradleBlueprintDependencyPlugin)

    // Quality
    // -> Detekt
    implementation(libs.gradleDetektPlugin)
    // -> Spotless
    implementation(libs.gradleSpotlessPlugin)
    implementation(libs.gradleKtlintPlugin)

    // Version
    implementation(libs.gradleGitVersionPlugin)
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.3.3"
    distributionType = Wrapper.DistributionType.ALL
}
