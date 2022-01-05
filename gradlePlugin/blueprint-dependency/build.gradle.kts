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
    implementation(libs.gradleVersionUpdatePlugin)
    implementation(libs.gradleVersionCatalogUpdatePlugin)
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.3.3"
    distributionType = Wrapper.DistributionType.ALL
}
