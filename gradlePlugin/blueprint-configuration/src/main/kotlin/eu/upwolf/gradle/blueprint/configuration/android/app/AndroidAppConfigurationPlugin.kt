/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.android.app

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import eu.upwolf.gradle.blueprint.configuration.android.AndroidConfig
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import eu.upwolf.gradle.blueprint.dependency.VersionHelper
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

@Suppress("UnstableApiUsage")
class AndroidAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "com.android.application")
        target.apply(plugin = "kotlin-android")
        target.apply(plugin = "kotlin-parcelize")

        setupAndroidApplication(target)
        setupAndroidKotlinCompatibility(target)
        setupDependencies(target)
    }

    private fun setupAndroidApplication(project: Project) {
        val versionHelper = VersionHelper(project)

        project.android {
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
                    isTestCoverageEnabled = true
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
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
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
            sourceCompatibility = JavaVersion.VERSION_1_8.toString()
            targetCompatibility = JavaVersion.VERSION_1_8.toString()

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()

                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
                )
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

    private fun Project.android(action: Action<BaseAppModuleExtension>) {
        extensions.configure(BaseAppModuleExtension::class.java, action)
    }

    private fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
        add("implementation", dependencyNotation)

    private fun DependencyHandler.`testImplementation`(dependencyNotation: Any): Dependency? =
        add("testImplementation", dependencyNotation)

    private fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? =
        add("androidTestImplementation", dependencyNotation)
}
