/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.android.library

import eu.upwolf.gradle.blueprint.configuration.AndroidConfig
import eu.upwolf.gradle.blueprint.configuration.androidLibrary
import eu.upwolf.gradle.blueprint.configuration.setupKotlinCompatibility
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("UnstableApiUsage")
class AndroidLibraryConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("com.android.library")

        setupAndroidLibrary(target)

        target.setupKotlinCompatibility()
    }

    private fun setupAndroidLibrary(project: Project) {
        project.androidLibrary {
            compileSdk = AndroidConfig.compileSdkVersion

            defaultConfig {
                minSdk = AndroidConfig.minSdkVersion
                targetSdk = AndroidConfig.targetSdkVersion

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testInstrumentationRunnerArguments += mapOf(
                    "clearPackageData" to "true"
                )

                consumerProguardFiles("consumer-rules.pro")
            }

            // FIXME
            //resourcePrefix(AndroidConfig.resourcePrefix)

            buildTypes {
                getByName("debug") {
                    matchingFallbacks += listOf("release")
                }
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    matchingFallbacks += listOf("release")
                }
            }

            lint {
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

            sourceSets {
                getByName("main") {
                    manifest.srcFile("src/androidMain/AndroidManifest.xml")
                    java.setSrcDirs(setOf("src/androidMain/kotlin"))
                    res.setSrcDirs(setOf("src/androidMain/res", "src/commonMain/resources"))
                }

                getByName("test") {
                    java.setSrcDirs(setOf("src/androidTest/kotlin"))
                    res.setSrcDirs(setOf("src/androidTest/res"))
                }
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
}
