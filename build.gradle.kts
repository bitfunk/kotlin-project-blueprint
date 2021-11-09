/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven {
            url = uri("https://maven.pkg.github.com/wmontwe/gradle-git-version")
            credentials {
                username = project.findProperty("githubPackageDownload.user") as? String
                    ?: System.getenv("GITHUB_PACKAGE_DOWNLOAD_USER")
                password = project.findProperty("githubPackageDownload.key") as? String
                    ?: System.getenv("GITHUB_PACKAGE_DOWNLOAD_KEY")
            }
        }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath("com.android.tools.build:gradle:_")
        classpath("com.squareup.sqldelight:gradle-plugin:_")
    }
}

plugins {
    id("eu.upwolf.gradle.blueprint.dependency")

    id("eu.upwolf.gradle.blueprint.quality.spotless")
    id("eu.upwolf.gradle.blueprint.quality.detekt")
    id("eu.upwolf.gradle.blueprint.version")
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            if ("org.jacoco" == requested.group) {
                useVersion("0.8.7")
            }
        }
    }
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.2"
    distributionType = Wrapper.DistributionType.ALL
}
