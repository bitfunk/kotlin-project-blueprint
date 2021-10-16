/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.publish

import eu.upwolf.gradle.blueprint.dependency.byEnv
import eu.upwolf.gradle.blueprint.dependency.byProperty
import org.gradle.api.Project

object SigningConfig {
    const val isSigningEnabled = false

    private const val SIGNING_KEY_PROPERTY_NAME = "signing.key"
    private const val SIGNING_PASSWORD_PROPERTY_NAME = "signing.password"

    private const val SIGNING_KEY_ENV_NAME = "SIGNING_KEY"
    private const val SIGNING_PASSWORD_ENV_NAME = "SIGNING_PASSWORD"

    fun loadSigningKey(project: Project): String? {
        return SIGNING_KEY_PROPERTY_NAME.byProperty(project) ?: SIGNING_KEY_ENV_NAME.byEnv()
    }

    fun loadSigningPassword(project: Project): String? {
        return SIGNING_PASSWORD_PROPERTY_NAME.byProperty(project) ?: SIGNING_PASSWORD_ENV_NAME.byEnv()
    }
}
