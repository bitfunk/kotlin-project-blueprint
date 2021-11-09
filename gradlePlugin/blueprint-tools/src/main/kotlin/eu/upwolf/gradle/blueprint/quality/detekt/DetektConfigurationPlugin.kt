/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.quality.detekt

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType

class DetektConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "io.gitlab.arturbosch.detekt")

        setupDetekt(target)

        target.tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            // Target version of the generated JVM bytecode. It is used for type resolution.
            this.jvmTarget = "1.8"
        }
    }

    private fun setupDetekt(project: Project) {
        project.detekt {
            toolVersion = detektVersion
            parallel = true

            source = project.files(
                project.file(project.rootDir)
            )

            config = project.rootProject.files("config/detekt/config.yml")
            baseline = project.rootProject.file("config/detekt/baseline.yml")

            reports {
                html.enabled = true
                xml.enabled = true
                txt.enabled = false
            }
        }

        project.tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            exclude(
                "**/.gradle/**",
                "**/.idea/**",
                "**/build/**",
                ".github/**",
                "gradle/**",
            )
        }
        project.tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
            exclude(
                "**/.gradle/**",
                "**/.idea/**",
                "**/build/**",
                ".github/**",
                "assets/**",
                "app-ios/**",
                "docs/**",
                "gradle/**",
                "**/*.adoc",
                "**/gradlew",
                "**/LICENSE",
                "**/.java-version",
                "**/gradlew.bat",
                "**/*.png",
                "**/*.properties",
                "**/*.pro",
                "**/*.sq",
                "**/*.xml",
                "**/*.yml",
            )
        }
    }

    private fun Project.detekt(action: Action<DetektExtension>) {
        extensions.configure(DetektExtension::class.java, action)
    }

    companion object {
        const val detektVersion = "1.18.1"
    }
}
