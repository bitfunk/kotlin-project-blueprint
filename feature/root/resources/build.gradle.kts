/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpCommon)
}

kotlin {
    ios {
        binaries {
            framework {
                baseName = "FeatureRootResources"
                transitiveExport = true
                export(projects.feature.splash.resources)
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.splash.resources)
            }
        }

        iosMain {
            dependencies {
                api(projects.feature.splash.resources)
            }
        }
    }
}
