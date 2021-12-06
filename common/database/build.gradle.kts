/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationKmpCommon)
    id("com.squareup.sqldelight")
}

sqldelight {
    database("CommonSqlDatabase") {
        packageName = "eu.upwolf.mobile.blueprint.common.database"
    }
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
            dependencies {
                implementation(libs.sqldelight.androidDriver)
            }
        }
        androidTest {
            dependencies {
                implementation(libs.sqldelight.sqliteDriver)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.sqldelight.nativeDriver)
            }
        }
        iosTest {
            // No addition
        }
    }
}
