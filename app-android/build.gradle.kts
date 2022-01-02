/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.version.version
import eu.upwolf.gradle.blueprint.version.versionCode

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationAndroidApp)
}

android {
    defaultConfig {
        applicationId = "eu.upwolf.mobile.blueprint.android"

        versionCode = versionCode()
        versionName = version()
    }
}

dependencies {
    implementation(projects.common.ui.system)

    implementation(projects.feature.root.component)
    implementation(projects.feature.root.ui)

    implementation(libs.decompose.core)
    implementation(libs.decompose.extensionJetpackCompose)

    // TODO check dependencies below
    implementation(libs.kotlin.android)

    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.activityCompose)

    implementation(libs.google.android.material)

    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insetsUi)

    implementation(libs.androidx.compose.ui.uiToolingPreview)

    testImplementation(libs.test.junit)

    androidTestImplementation(libs.test.androidx.runner)
    androidTestImplementation(libs.test.androidx.rules)
    androidTestImplementation(libs.test.androidx.orchestrator)
    androidTestImplementation(libs.test.androidx.extJunitKtx)

    androidTestImplementation(libs.test.androidx.espresso.core)
    androidTestImplementation(libs.test.androidx.espresso.intents)
    androidTestImplementation(libs.test.androidx.espresso.web)

    androidTestImplementation(libs.test.androidx.uiautomator)

    androidTestImplementation(libs.test.androidx.compose.ui.junit4)
    androidTestImplementation(libs.test.android.kakaocup.compose)
}
