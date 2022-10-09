/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.kmp.common

import eu.upwolf.gradle.blueprint.configuration.fixAndroidSourceSets
import eu.upwolf.gradle.blueprint.dependency.DependencyHelper
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.compose
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
        setupDesktopTarget(project)
        setupIosTarget(project)
        setupWebTarget(project)
    }

    private fun setupAndroidTarget(project: Project) {
        val libs = DependencyHelper(project)

        project.kotlin {
            android {
                publishLibraryVariants("release")
            }

            sourceSets {
                maybeCreate("androidMain").dependencies {
                    // nothing to add
                }
                val androidTest = maybeCreate("androidTest")
                androidTest.dependencies {
                    implementation(libs.test.kotlin.junit)
                    implementation(libs.test.junit)
                }
                fixAndroidSourceSets(androidTest)
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
                    // nothing to add
                }
                maybeCreate("jvmTest").dependencies {
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

    private fun setupWebTarget(project: Project) {
        project.kotlin {
            project.kotlin {
                js(IR) {
                    browser()
                }

                sourceSets {
                    maybeCreate("jsMain").dependencies {
                        api(ComposePlugin.Dependencies.runtime)
                        implementation(ComposePlugin.Dependencies.web.core)
                    }
                    maybeCreate("jsTest").dependencies {
                        // nothing to add
                    }
                }
            }
        }
    }

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }
}
