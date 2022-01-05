/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.app.desktop

import eu.upwolf.gradle.blueprint.configuration.kotlin
import eu.upwolf.gradle.blueprint.configuration.setupKotlinCompatibility
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.compose.compose

class DesktopAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        target.pluginManager.apply("org.jetbrains.compose")

        target.repositories {
            mavenCentral()
            google()
            maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }

        setupDesktopApplication(target)

        target.setupKotlinCompatibility(
            listOf(
                "-opt-in=kotlin.RequiresOptIn"
            )
        )
    }

    private fun setupDesktopApplication(project: Project) {
        project.kotlin {
            jvm("desktop") {
                compilations.all {
                    kotlinOptions.jvmTarget = "11"
                }
            }

            sourceSets {
                maybeCreate("desktopMain").dependencies {
                    api(compose.runtime)
                    api(compose.foundation)
                    api(compose.material)
                    implementation(compose.desktop.currentOs)
                }
                maybeCreate("desktopTest").dependencies {
                    // nothing to add
                }
            }
        }
    }
}
