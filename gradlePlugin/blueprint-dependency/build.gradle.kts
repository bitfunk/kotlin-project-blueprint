/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
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
    implementation("com.github.ben-manes:gradle-versions-plugin:_")
}
