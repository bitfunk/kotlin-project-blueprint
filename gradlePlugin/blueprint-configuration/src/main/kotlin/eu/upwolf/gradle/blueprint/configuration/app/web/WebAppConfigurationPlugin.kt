/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.app.web

import eu.upwolf.gradle.blueprint.configuration.kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.compose

class WebAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        target.pluginManager.apply("org.jetbrains.compose")

        target.repositories {
            mavenCentral()
            google()
            maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }

        setupWebApplication(target)
    }

    private fun setupWebApplication(project: Project) {
        project.kotlin {
            js("web", IR) {
                browser {
                    binaries.executable()
                    testTask {
                        useKarma {
                            useChromeHeadless()
                        }
                    }
                }
            }

            sourceSets {
                maybeCreate("webMain").dependencies {
                    api(ComposePlugin.Dependencies.runtime)
                    implementation(ComposePlugin.Dependencies.web.core)
                }
                maybeCreate("webTest").dependencies {
                    // nothing to add
                }
            }
        }
    }
}
