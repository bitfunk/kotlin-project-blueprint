/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

plugins {
    id("eu.upwolf.gradle.blueprint.configuration.kmp.feature")
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

android {
    packagingOptions {
        resources {
            excludes += setOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
            )
        }
    }
}
