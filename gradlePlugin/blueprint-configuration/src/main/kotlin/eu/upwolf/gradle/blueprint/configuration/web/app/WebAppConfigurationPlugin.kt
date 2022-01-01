/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.web.app

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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
            js(IR) {
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
                maybeCreate("jsMain").dependencies {
                    api(compose.runtime)
                    implementation(compose.web.core)
                }
                maybeCreate("jsTest").dependencies {
                    // nothing to add
                }
            }
        }
    }

    private fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
        extensions.configure(KotlinMultiplatformExtension::class.java, action)
    }
}
