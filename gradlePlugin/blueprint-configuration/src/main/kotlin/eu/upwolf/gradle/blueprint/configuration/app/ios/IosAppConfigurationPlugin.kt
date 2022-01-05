/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.app.ios

import eu.upwolf.gradle.blueprint.configuration.apple
import org.gradle.api.Plugin
import org.gradle.api.Project

class IosAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply("org.jetbrains.gradle.apple.applePlugin")

        setupIosApplication(target)
    }

    private fun setupIosApplication(project: Project) {
        project.apple {
            iosApp {
                sceneDelegateClass = "SceneDelegate"
                launchStoryboard = "LaunchScreen"

                dependencies {
                    // nothing
                }
            }
        }
    }
}
