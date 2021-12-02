/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.kmp.common.compose

import eu.upwolf.gradle.blueprint.dependency.Dependency
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

class CommonComposeConfigurationPlugin : Plugin<Project> {

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

    @OptIn(ExperimentalComposeLibrary::class)
    private fun setupMultiplatformLibrary(project: Project) {
        project.kotlin {
            sourceSets {
                all {
                    all {
                        languageSettings.optIn("org.jetbrains.compose.ExperimentalComposeLibrary")
                    }
                }

                maybeCreate("commonMain").dependencies {
                    api(compose.runtime)
                    api(compose.foundation)
                    api(compose.material)
                    api(compose.material3)
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
        project.kotlin {
            android {
                publishLibraryVariants("release")
            }

            sourceSets {
                maybeCreate("androidMain").dependencies {
                    implementation(Dependency.Kotlin.StdLib.android)
                    implementation(Dependency.Kotlin.Coroutines.android)
                }
                maybeCreate("androidTest").dependencies {
                    implementation(Dependency.Kotlin.Test.jvmJunit)
                    implementation(Dependency.Kotlin.Coroutines.test)
                }
            }
        }
    }

    private fun setupDesktopTarget(project: Project) {
        project.kotlin {
            jvm {

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

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }
}
