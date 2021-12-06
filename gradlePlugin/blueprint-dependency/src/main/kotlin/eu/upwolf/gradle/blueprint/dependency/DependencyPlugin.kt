/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named

class DependencyPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(plugin = "com.github.ben-manes.versions")
        target.apply(plugin = "nl.littlerobots.version-catalog-update")

        target.tasks.named<DependencyUpdatesTask>("dependencyUpdates")
            .configure {
                // reject all non stable versions
                // and disallow release candidates as upgradable versions from stable versions
                resolutionStrategy {
                    componentSelection {
                        all {
                            if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                                reject("Release candidate")
                            }
                        }
                    }
                }
            }
    }

    private fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}
