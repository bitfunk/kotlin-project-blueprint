/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.configuration

import org.gradle.api.Action
import org.gradle.api.Project
import org.jetbrains.gradle.apple.AppleProjectExtension

internal fun Project.apple(action: Action<AppleProjectExtension>) {
    extensions.configure(AppleProjectExtension::class.java, action)
}
