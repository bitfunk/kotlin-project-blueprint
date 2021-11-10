/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.Dependency

plugins {
    id("eu.upwolf.gradle.blueprint.configuration.kmp.common")
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
                implementation(Dependency.multiplatform.sqlDelight.driverAndroid)
            }
        }
        androidTest {
            dependencies {
                implementation(Dependency.multiplatform.sqlDelight.driverJvm)
            }
        }

        iosMain {
            dependencies {
                implementation(Dependency.multiplatform.sqlDelight.driverIos)
            }
        }
        iosTest {
            // No addition
        }
    }
}
