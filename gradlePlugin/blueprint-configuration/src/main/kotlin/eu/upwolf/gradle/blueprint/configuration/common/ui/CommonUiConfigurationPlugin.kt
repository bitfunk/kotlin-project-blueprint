/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.common.ui

import eu.upwolf.gradle.blueprint.configuration.androidLibrary
import eu.upwolf.gradle.blueprint.configuration.fixAndroidSourceSets
import eu.upwolf.gradle.blueprint.configuration.kotlin
import eu.upwolf.gradle.blueprint.configuration.setupKotlinCompatibility
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import eu.upwolf.gradle.blueprint.dependency.VersionHelper
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.compose

@Suppress("UnstableApiUsage")
class CommonUiConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        target.pluginManager.apply("eu.upwolf.gradle.blueprint.configuration.android.library")
        target.pluginManager.apply("org.jetbrains.compose")

        target.repositories {
            mavenCentral()
            google()
            maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }

        setupTargets(target)

        target.setupKotlinCompatibility(
            listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
            )
        )
    }

    private fun setupTargets(project: Project) {
        setupCommonTarget(project)
        setupAndroidTarget(project)
        setupDesktopTarget(project)
    }

    @OptIn(ExperimentalComposeLibrary::class)
    private fun setupCommonTarget(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            sourceSets {
                all {
                    languageSettings.apply {
                        optIn("org.jetbrains.compose.ExperimentalComposeLibrary")
                    }
                }

                maybeCreate("commonMain").dependencies {
                    api(ComposePlugin.Dependencies.runtime)
                    api(ComposePlugin.Dependencies.foundation)
                    api(ComposePlugin.Dependencies.material)
                    api(ComposePlugin.Dependencies.material3)
                    api(ComposePlugin.Dependencies.animation)
                }

                maybeCreate("commonTest").dependencies {
                    implementation(libs.test.kotlin.core)
                    implementation(libs.test.kotlin.annotations)
                }
            }
        }
    }

    private fun setupAndroidTarget(project: Project) {
        val versionHelper = VersionHelper(project)
        val libs = DependencyHelper(project)

        project.kotlin {
            android {
                publishLibraryVariants("release")
            }

            sourceSets {
                maybeCreate("androidMain").dependencies {
                    implementation(libs.kotlin.android)
                    implementation(libs.kotlinx.coroutines.android)
                    implementation(libs.androidx.compose.compiler)
                    implementation(libs.androidx.compose.runtime)
                    implementation(libs.androidx.compose.foundation)
                    implementation(libs.androidx.compose.ui)
                    implementation(libs.androidx.compose.material)
                    implementation(libs.androidx.compose.material3)
                    implementation(libs.androidx.compose.uiToolingPreview)
                }
                val androidTest = maybeCreate("androidTest")
                androidTest.dependencies {
                    implementation(libs.test.kotlin.junit)
                    implementation(libs.test.junit)
                }
                fixAndroidSourceSets(androidTest)
            }
        }

        project.androidLibrary {
            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = versionHelper.androidX.compose.compiler
            }
        }
    }

    private fun setupDesktopTarget(project: Project) {
        project.kotlin {
            jvm("desktop") {
                compilations.all {
                    kotlinOptions.jvmTarget = "11"
                }
            }

            sourceSets {
                maybeCreate("desktopMain").dependencies {
                    implementation(ComposePlugin.Dependencies.desktop.currentOs)
                }
                maybeCreate("desktopTest").dependencies {
                    // nothing to add
                }
            }
        }
    }
}
