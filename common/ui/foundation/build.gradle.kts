/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationCommonUi)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.resources)
            }
        }
        androidMain {
            dependencies {
                api(libs.androidx.appCompat)
            }
        }
    }
}

android {
    namespace = "eu.upwolf.mobile.blueprint.common.ui.foundation"
}
