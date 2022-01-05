/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationAppWeb)
}

kotlin {
    sourceSets {
        jsMain {
            dependencies {
                implementation(projects.feature.root.component)
            }
        }
    }
}
