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
                api(projects.common.ui.system)
                implementation(libs.decompose.core)

                implementation(projects.feature.splash.component)
            }
        }
    }
}
