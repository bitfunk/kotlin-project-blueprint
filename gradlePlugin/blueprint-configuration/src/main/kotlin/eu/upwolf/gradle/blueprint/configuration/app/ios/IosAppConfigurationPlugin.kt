/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration.app.ios

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.gradle.apple.AppleProjectExtension

class IosAppConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "org.jetbrains.gradle.apple.applePlugin")

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

    private fun Project.apple(action: Action<AppleProjectExtension>) {
        extensions.configure(AppleProjectExtension::class.java, action)
    }
}
