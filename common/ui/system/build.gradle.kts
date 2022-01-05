/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpCompose)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.common.ui.theme)
                api(projects.common.ui.foundation)
            }
        }
    }
}
