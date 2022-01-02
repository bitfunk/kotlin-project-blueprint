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
    ios {
        binaries {
            framework {
                baseName = "Blueprint"
                transitiveExport = true
                linkerOpts.add("-lsqlite3")
                export(projects.common.database)
                export(libs.decompose.core)

                export(projects.feature.splash.component)
                export(projects.feature.home.component)
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.database)
                implementation(libs.decompose.core)

                implementation(projects.feature.splash.component)
                implementation(projects.feature.home.component)
            }
        }

        iosMain {
            dependencies {
                api(projects.common.database)
                api(libs.decompose.core)

                api(projects.feature.splash.component)
                implementation(projects.feature.home)
            }
        }
    }
}
