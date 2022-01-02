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
                implementation(libs.decompose.extensionJetbrainsCompose)

                implementation(projects.feature.root.component)

                implementation(projects.feature.splash.component)
                implementation(projects.feature.splash.ui)
                implementation(projects.feature.home.component)
                implementation(projects.feature.home.ui)
            }
        }
    }
}
