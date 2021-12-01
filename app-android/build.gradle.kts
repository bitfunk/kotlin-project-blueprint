/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.version.version
import eu.upwolf.gradle.blueprint.version.versionCode

plugins {
    id("eu.upwolf.gradle.blueprint.configuration.android.app")
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
        kotlinCompilerExtensionVersion = eu.upwolf.gradle.blueprint.dependency.Version.android.androidX.compose.core
    }
}

dependencies {
    implementation(Kotlin.stdlib.jdk7)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.compose.ui)
    implementation("androidx.compose.material3:material3:_")
    implementation(AndroidX.compose.material)
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.activity.compose)

    implementation(Google.android.material)
    implementation(Google.accompanist.insets)
    implementation(Google.accompanist.insets.ui)
    implementation(Google.accompanist.systemuicontroller)

    implementation("com.arkivanov.decompose:decompose:_")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:_")

    implementation(AndroidX.compose.ui.toolingPreview)

    debugImplementation(AndroidX.compose.ui.tooling)

    testImplementation(Testing.junit4)

    androidTestImplementation(AndroidX.test.ext.junit)

    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.test.espresso.intents)
    androidTestImplementation(AndroidX.test.espresso.web)

    androidTestImplementation(AndroidX.test.uiAutomator)

    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    androidTestImplementation("io.github.kakaocup:compose:_")

    // old
    implementation(AndroidX.lifecycle.extensions)
    implementation(AndroidX.lifecycle.commonJava8)

    androidTestImplementation(AndroidX.test.runner)
    androidTestImplementation(AndroidX.test.rules)
    androidTestImplementation(AndroidX.test.orchestrator)
}
