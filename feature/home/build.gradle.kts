/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpFeature)
}

kotlin {
    sourceSets {
        commonMain {
            // No addition
        }
        commonTest {
            // No addition
        }

        androidMain {
            // No addition
        }
        androidTest {
            // No addition
        }

        iosMain {
            // No addition
        }
        iosTest {
            // No addition
        }
    }
}
