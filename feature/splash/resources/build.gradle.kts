/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpCommon)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.resources)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "eu.upwolf.mobile.blueprint.feature.splash.resources"
}
