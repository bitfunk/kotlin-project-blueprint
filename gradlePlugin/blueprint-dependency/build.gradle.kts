/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

    alias(libs.plugins.gradleVersionUpdate)
    alias(libs.plugins.gradleVersionCatalogUpdate)
}

// To make it available as direct dependency
group = "eu.upwolf.gradle.blueprint.dependency"
version = "1.0.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

gradlePlugin {
    plugins.register("blueprint-dependency") {
        id = "eu.upwolf.gradle.blueprint.dependency"
        implementationClass = "eu.upwolf.gradle.blueprint.dependency.DependencyPlugin"
    }
}

dependencies {
    implementation(libs.gradleVersionUpdatePlugin)
    implementation(libs.gradleVersionCatalogUpdatePlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.3.3"
    distributionType = Wrapper.DistributionType.ALL
}
