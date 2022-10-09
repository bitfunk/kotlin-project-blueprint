/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationFeatureResource)
}

kotlin {
    ios {
        binaries {
            framework {
                baseName = "FeatureSplashResources"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                // nothing to add
            }
        }
    }
}

android {
    namespace = "eu.upwolf.mobile.blueprint.feature.splash.resources"
}

multiplatformResources {
    multiplatformResourcesPackage = "eu.upwolf.mobile.blueprint.feature.splash.resources"
}
