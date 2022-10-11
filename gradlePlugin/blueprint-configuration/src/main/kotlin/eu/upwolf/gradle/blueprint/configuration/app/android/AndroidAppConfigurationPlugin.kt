/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.app.android

import eu.upwolf.gradle.blueprint.configuration.AndroidConfig
import eu.upwolf.gradle.blueprint.configuration.androidApp
import eu.upwolf.gradle.blueprint.configuration.implementation
import eu.upwolf.gradle.blueprint.configuration.setupKotlinCompatibility
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import eu.upwolf.gradle.blueprint.dependency.VersionHelper
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

@Suppress("UnstableApiUsage")
class AndroidAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("com.android.application")
        target.pluginManager.apply("kotlin-android")
        target.pluginManager.apply("kotlin-parcelize")

        setupAndroidApplication(target)
        setupDependencies(target)
        setupAndroidKotlinCompatibility(target)
    }

    private fun setupAndroidApplication(project: Project) {
        val versionHelper = VersionHelper(project)

        project.androidApp {
            compileSdk = AndroidConfig.compileSdkVersion

            defaultConfig {
                minSdk = AndroidConfig.minSdkVersion
                targetSdk = AndroidConfig.targetSdkVersion

                vectorDrawables.useSupportLibrary = true

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testInstrumentationRunnerArguments += mapOf(
                    "clearPackageData" to "true"
                )
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = versionHelper.androidX.compose.compiler
            }

            buildTypes {
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                    versionNameSuffix = "-DEBUG"
                    enableUnitTestCoverage = true
                    enableAndroidTestCoverage = true
                    matchingFallbacks += listOf("release")
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro"
                    )
                    matchingFallbacks += listOf("release")
                }
            }

            lint {
                baseline = File("lint-baseline.xml")

                disable += setOf("Typos")

                warningsAsErrors = true
                abortOnError = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            testOptions {
                animationsDisabled = true
            }

            packagingOptions {
                resources {
                    excludes += setOf(
                        "META-INF/AL2.0",
                        "META-INF/LGPL2.1",
                    )
                }
            }
        }
    }

    private fun setupAndroidKotlinCompatibility(project: Project) {
        project.tasks.withType(KotlinCompile::class.java).all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }

    private fun setupDependencies(project: Project) {
        val libs = DependencyHelper(project)

        project.dependencies {
            implementation(libs.androidx.compose.material)
            implementation(libs.androidx.compose.material3)
        }
    }
}
