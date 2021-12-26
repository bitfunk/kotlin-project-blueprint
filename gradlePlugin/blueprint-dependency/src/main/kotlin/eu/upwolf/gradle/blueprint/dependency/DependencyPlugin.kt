/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import nl.littlerobots.vcu.plugin.versionCatalogUpdate
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
                            if (isStable(currentVersion) && !isStable(candidate.version)) {
                                reject("Release candidate")
                            }
                        }
                    }
                }
            }

        target.versionCatalogUpdate {
            keepUnused = true
            sortByKey = true
        }
    }

    private fun isStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^([0-9]+)\\.([0-9]+)\\.([0-9]+)\$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable
    }
}
