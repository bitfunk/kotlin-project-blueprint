/*
 * ISC License
 *
 * Copyright (c) 2022. Wolf-Martell Montwé (bitfunk)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
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
        }

        project.tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            exclude(
                "**/.gradle/**",
                "**/.idea/**",
                "**/build/**",
                ".github/**",
                "gradle/**",
            )
            reports {
                xml.required.set(true)
                html.required.set(true)
            }
        }
        project.tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
            exclude(
                "**/.gradle/**",
                "**/.idea/**",
                "**/build/**",
                "**/gradle/wrapper/**",
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
