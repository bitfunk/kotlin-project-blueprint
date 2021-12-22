/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.android.library

import com.android.build.gradle.LibraryExtension
import eu.upwolf.gradle.blueprint.configuration.android.AndroidConfig
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("UnstableApiUsage")
class AndroidLibraryConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "com.android.library")

        setupAndroidLibrary(target)
        setupAndroidKotlinCompatibility(target)
    }

    private fun setupAndroidLibrary(project: Project) {
        project.android {
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

            resourcePrefix(AndroidConfig.resourcePrefix)

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
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            testOptions {
                animationsDisabled = true
            }

            sourceSets {
                getByName("main") {
                    manifest.srcFile("src/androidMain/AndroidManifest.xml")
                    java.setSrcDirs(setOf("src/androidMain/kotlin"))
                    res.setSrcDirs(setOf("src/androidMain/res"))
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

    private fun setupAndroidKotlinCompatibility(project: Project) {
        project.tasks.withType(KotlinCompile::class.java).all {
            sourceCompatibility = JavaVersion.VERSION_1_8.toString()
            targetCompatibility = JavaVersion.VERSION_1_8.toString()

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    private fun Project.android(action: Action<LibraryExtension>) {
        extensions.configure(LibraryExtension::class.java, action)
    }
}
