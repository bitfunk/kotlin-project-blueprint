/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpCommonCompose)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":common:theme"))
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.compose.ui.uiToolingPreview)
            }
        }
    }
}
