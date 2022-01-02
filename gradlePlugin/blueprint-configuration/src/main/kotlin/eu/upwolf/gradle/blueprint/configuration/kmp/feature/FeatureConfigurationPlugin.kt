/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.kmp.feature

import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.util.Locale

class FeatureConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("eu.upwolf.gradle.blueprint.configuration.kmp.common")
        target.pluginManager.apply("kotlinx-serialization")

        setupMultiplatformLibrary(target)
        setupTargets(target)
    }

    private fun setupMultiplatformLibrary(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            sourceSets {
                maybeCreate("commonMain").dependencies {
                    implementation(libs.decompose.core)
                    implementation(libs.koin.core)
                }

                maybeCreate("commonTest").dependencies {
                    implementation(libs.koin.test)
                }
            }
        }
    }

    private fun setupTargets(project: Project) {
        setupAndroidTarget(project)
        setupIosTarget(project)
    }

    private fun setupAndroidTarget(project: Project) {
        project.kotlin {
            sourceSets {
                maybeCreate("androidMain").dependencies {
                    // Nothing to add
                }
                maybeCreate("androidTest").dependencies {
                    // Nothing to add
                }
            }
        }
    }

    private fun setupIosTarget(project: Project) {
        project.kotlin {
            ios {
                binaries {
                    framework("Feature${project.name.capitalize(Locale.ENGLISH)}")
                }
            }

            sourceSets {
                maybeCreate("iosMain").dependencies {
                    // Nothing to add
                }
                maybeCreate("iosTest").dependencies {
                    // Nothing to add
                }
            }
        }
    }

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }
}
