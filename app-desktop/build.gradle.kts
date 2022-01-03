/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.version.versionCleaned
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationDesktopApp)
}

kotlin {
    sourceSets {
        jvmMain {
            dependencies {
                implementation(libs.decompose.core)
                implementation(libs.decompose.extensionJetbrainsCompose)

                implementation(projects.common.ui.system)

                implementation(projects.feature.root.component)
                implementation(projects.feature.root.ui)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "eu.upwolf.mobile.blueprint.desktop.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "Blueprint"
            packageVersion = versionCleaned()

            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "e12995e4-1d13-4da1-9730-baef8acb4ede"
            }
        }
    }
}
