/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.kmp.compose

import com.android.build.gradle.BaseExtension
import eu.upwolf.gradle.blueprint.dependency.Dependency
import eu.upwolf.gradle.blueprint.dependency.Version
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
                    implementation(Dependency.android.androidX.compose.compiler)
                    implementation(Dependency.android.androidX.compose.runtime)
                    implementation(Dependency.android.androidX.compose.foundation)
                    implementation(Dependency.android.androidX.compose.ui)
                    implementation(Dependency.android.androidX.compose.material)
                }
                maybeCreate("androidTest").dependencies {
                    implementation(Dependency.Kotlin.Test.jvmJunit)
                    implementation(Dependency.Kotlin.Coroutines.test)
                }
            }
        }

        project.android {
            composeOptions {
                kotlinCompilerExtensionVersion = Version.android.androidX.compose.compiler
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

    private fun Project.android(action: Action<BaseExtension>) {
        extensions.configure(BaseExtension::class.java, action)
    }
}
