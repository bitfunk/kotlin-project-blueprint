/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.quality.spotless

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class SpotlessConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "com.diffplug.spotless")

        setupSpotless(target)
    }

    private fun setupSpotless(project: Project) {
        project.spotless {
            kotlin {
                target("**/*.kt")
                targetExclude("**/build/")
                ktlint(ktlintVersion)
                trimTrailingWhitespace()
                indentWithSpaces()
                endWithNewline()
            }
            kotlinGradle {
                target("**/*.gradle.kts", "**/*.df.kts")
                ktlint(ktlintVersion)
                trimTrailingWhitespace()
                indentWithSpaces()
                endWithNewline()
            }
            format("misc") {
                target("**/*.adoc", "**/*.md", "**/.gitignore", ".java-version")
                trimTrailingWhitespace()
                indentWithSpaces()
                endWithNewline()
            }
        }
    }

    private fun Project.spotless(action: Action<SpotlessExtension>) {
        extensions.configure(SpotlessExtension::class.java, action)
    }

    companion object {
        const val ktlintVersion = "0.42.1"
    }
}
