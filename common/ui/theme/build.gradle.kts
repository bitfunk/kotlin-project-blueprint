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
                api(libs.jetbrains.compose.runtime)
                api(libs.jetbrains.compose.foundation)
                api(libs.jetbrains.compose.material)
                api(libs.jetbrains.compose.material3)
                api(libs.jetbrains.compose.animation)
            }
        }

        androidMain {
            dependencies {
                api(libs.accompanist.systemuicontroller)
            }
        }
    }
}

android {
    namespace = "eu.upwolf.mobile.blueprint.common.ui.theme"
}
