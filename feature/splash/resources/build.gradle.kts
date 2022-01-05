/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationFeatureResource)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // nothing to add
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "eu.upwolf.mobile.blueprint.feature.splash.resources"
}
