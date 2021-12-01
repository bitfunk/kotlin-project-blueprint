/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.android.app

import com.android.build.gradle.BaseExtension
import eu.upwolf.gradle.blueprint.configuration.android.AndroidConfig
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "com.android.application")
        target.apply(plugin = "kotlin-android")
        target.apply(plugin = "kotlin-parcelize")

        setupAndroidApplication(target)
        setupAndroidKotlinCompatibility(target)
    }

    private fun setupAndroidApplication(project: Project) {
        project.android {
            compileSdkVersion(AndroidConfig.compileSdkVersion)

            defaultConfig {
                minSdk = AndroidConfig.minSdkVersion
                targetSdk = AndroidConfig.targetSdkVersion

                vectorDrawables.useSupportLibrary = true

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testInstrumentationRunnerArguments(
                    mapOf(
                        "clearPackageData" to "true"
                    )
                )
            }

            buildTypes {
                getByName("debug") {
                    setMatchingFallbacks("release")
                    applicationIdSuffix = ".debug"
                    versionNameSuffix = "-DEBUG"
                    isTestCoverageEnabled = true
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro"
                    )
                    setMatchingFallbacks("release")
                }
            }

            lintOptions {
                isWarningsAsErrors = true
                isAbortOnError = true
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
            }
        }
    }

    private fun Project.android(action: Action<BaseExtension>) {
        extensions.configure(BaseExtension::class.java, action)
    }
}
