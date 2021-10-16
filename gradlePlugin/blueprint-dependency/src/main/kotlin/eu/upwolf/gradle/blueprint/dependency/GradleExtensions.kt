/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

import org.gradle.api.Project

fun String.byProperty(project: Project) = project.findProperty(this) as? String

fun String.byEnv(): String? = System.getenv(this)
