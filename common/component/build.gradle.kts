/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.Dependency

plugins {
    id("eu.upwolf.gradle.blueprint.configuration.kmp.common.compose")
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
                implementation(AndroidX.compose.ui.toolingPreview)
            }
        }
    }
}
