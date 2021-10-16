/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import eu.upwolf.gradle.blueprint.dependency.Dependency
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

    packagingOptions {
        resources {
            excludes += setOf(
                "META-INF/LGPL2.1",
                "META-INF/AL2.0"
            )
        }
    }
}

dependencies {
    implementation(Kotlin.stdlib.jdk7)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.material)
    implementation("androidx.compose.material3:material3:_")
    implementation(Dependency.android.androidX.compose.navigation)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.activity.compose)

    implementation(Dependency.android.androidX.compose.accompanistInsets)
    implementation(Dependency.android.androidX.compose.accompanistInsetsUi)
    implementation(Dependency.android.androidX.compose.accompanistSystemUiController)

    testImplementation(Testing.junit4)

    androidTestImplementation(AndroidX.test.ext.junit)

    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.test.espresso.intents)
    androidTestImplementation(AndroidX.test.espresso.web)

    androidTestImplementation(AndroidX.test.uiAutomator)

    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    androidTestImplementation(Dependency.androidTest.kakaoCompose)

    debugImplementation(AndroidX.compose.ui.tooling)

    // old
    implementation(AndroidX.lifecycle.extensions)
    implementation(AndroidX.lifecycle.commonJava8)

    androidTestImplementation(AndroidX.test.runner)
    androidTestImplementation(AndroidX.test.rules)
    androidTestImplementation(AndroidX.test.orchestrator)

    androidTestImplementation(Dependency.androidTest.kakao)
}
