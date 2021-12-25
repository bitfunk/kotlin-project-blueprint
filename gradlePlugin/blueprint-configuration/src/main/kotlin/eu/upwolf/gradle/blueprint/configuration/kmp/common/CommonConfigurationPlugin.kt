/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.kmp.common

import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CommonConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        target.pluginManager.apply("eu.upwolf.gradle.blueprint.configuration.android.library")

        setupMultiplatformLibrary(target)
        setupTargets(target)
    }

    private fun setupMultiplatformLibrary(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            sourceSets {
                maybeCreate("commonMain").dependencies {
                    implementation(libs.kotlin.core)
                    implementation(libs.kotlinx.coroutines.core)
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
        setupIosTarget(project)
    }

    private fun setupAndroidTarget(project: Project) {
        project.kotlin {
            android {
                publishLibraryVariants("release")
            }

            sourceSets {
                maybeCreate("androidMain").dependencies {
                    // nothing to add
                }
                maybeCreate("androidTest").dependencies {
                    // nothing to add
                }
            }
        }
    }

    private fun setupIosTarget(project: Project) {
        project.kotlin {
            ios { }

            sourceSets {
                maybeCreate("iosMain").dependencies {
                    // nothing to add
                }
            }
        }
    }

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }
}
