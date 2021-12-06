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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":common:theme"))

    implementation(libs.kotlin.android)

    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.activityCompose)

    implementation(libs.google.android.material)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)

    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insetsUi)

    implementation(libs.decompose)
    implementation(libs.decomposeExtensionJetpackCompose)

    // old
    implementation("androidx.compose.ui:ui:1.1.0-beta04")

    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0-beta04")

    debugImplementation("androidx.compose.ui:ui-tooling:1.1.0-beta04")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test:orchestrator:1.4.0")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-web:3.4.0")

    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-beta04")
    androidTestImplementation("io.github.kakaocup:compose:0.0.6")
}
