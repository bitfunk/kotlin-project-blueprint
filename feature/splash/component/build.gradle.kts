/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

// TODO could also be called app-root
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpCommon)
    id("org.jetbrains.kotlin.plugin.parcelize")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.decompose.core)
            }
        }
    }
}
