/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration

import org.gradle.api.Action
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.kotlin(action: Action<KotlinMultiplatformExtension>) {
    extensions.configure(KotlinMultiplatformExtension::class.java, action)
}

internal fun Project.setupKotlinCompatibility(compilerArgs: List<String> = emptyList()) {
    project.tasks.withType(KotlinCompile::class.java).all {
        sourceCompatibility = "11"
        targetCompatibility = "11"

        kotlinOptions {
            jvmTarget = "11"

            freeCompilerArgs = freeCompilerArgs + compilerArgs
        }
    }
}
