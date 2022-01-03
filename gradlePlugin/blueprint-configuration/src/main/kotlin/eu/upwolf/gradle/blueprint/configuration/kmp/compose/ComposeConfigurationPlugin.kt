/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.kmp.compose

import com.android.build.gradle.LibraryExtension
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import eu.upwolf.gradle.blueprint.dependency.VersionHelper
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("UnstableApiUsage")
class ComposeConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        target.pluginManager.apply("eu.upwolf.gradle.blueprint.configuration.android.library")
        target.pluginManager.apply("org.jetbrains.compose")

        target.repositories {
            mavenCentral()
            google()
            maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }

        setupMultiplatformLibrary(target)
        setupTargets(target)
    }

    private fun setupMultiplatformLibrary(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            sourceSets {
                all {
                    languageSettings.apply {
                        optIn("org.jetbrains.compose.ExperimentalComposeLibrary")
                    }
                }

                @OptIn(ExperimentalComposeLibrary::class)
                maybeCreate("commonMain").dependencies {
                    api(compose.runtime)
                    api(compose.foundation)
                    api(compose.material)
                    api(compose.material3)
                    api(compose.materialIconsExtended)
                    api(compose.animation)
                }

                maybeCreate("commonTest").dependencies {
                    implementation(libs.test.kotlin.core)
                    implementation(libs.test.kotlin.annotations)
                }
            }
        }

        project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    private fun setupTargets(project: Project) {
        setupAndroidTarget(project)
        setupDesktopTarget(project)
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
                    implementation(libs.androidx.compose.uiToolingPreview)
                }
                maybeCreate("androidTest").dependencies {
                    implementation(libs.test.kotlin.junit)
                    implementation(libs.test.junit)
                }
            }
        }

        project.android {
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
            jvm {
                compilations.all {
                    kotlinOptions.jvmTarget = "11"
                }
            }

            sourceSets {
                maybeCreate("jvmMain").dependencies {
                    implementation(compose.desktop.currentOs)
                }
                maybeCreate("jvmTest").dependencies {
                    // nothing to add
                }
            }
        }
    }

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }

    private fun Project.android(action: Action<LibraryExtension>) {
        extensions.configure(LibraryExtension::class.java, action)
    }
}
