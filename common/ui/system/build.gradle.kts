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
                api(projects.common.ui.theme)
                api(projects.common.ui.foundation)
            }
        }
    }
}

android {
    namespace = "eu.upwolf.mobile.blueprint.common.ui.system"
}
