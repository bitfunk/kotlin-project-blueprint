/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

object Dependency {

    val kotlin = Kotlin
    val multiplatform = Multiplatform
    val multiplatformTest = MultiplatformTest
    val jvmTest = JvmTest
    val android = Android
    val androidTest = AndroidTest

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    }

    object Multiplatform {

        val kotlin = Kotlin
        val ktor = Ktor
        val sqlDelight = SqlDelight
        val koin = Koin
        val klock = Klock
        val logger = Logger

        object Kotlin {

            val stdLib = StdLib
            val coroutines = Coroutines
            val serialization = Serialization

            object StdLib {
                const val common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Version.kotlin}"
                const val android = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
                const val jdk = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
                const val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlin}"
                const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Version.kotlin}"
            }

            object Coroutines {
                const val common =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Multiplatform.Kotlin.coroutines}"
                const val android =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Multiplatform.Kotlin.coroutines}"
                const val test =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.Multiplatform.Kotlin.coroutines}"
            }

            object Serialization {
                const val common =
                    "org.jetbrains.kotlinx:kotlinx-serialization-core:${Version.Multiplatform.Kotlin.serialization}"
                const val json =
                    "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.Multiplatform.Kotlin.serialization}"
            }

            const val dateTime =
                "org.jetbrains.kotlinx:kotlinx-datetime:${Version.Multiplatform.Kotlin.dateTime}"
        }

        object Ktor {
            const val commonCore = "io.ktor:ktor-client-core:${Version.Multiplatform.ktor}"
            const val commonJson = "io.ktor:ktor-client-json:${Version.Multiplatform.ktor}"
            const val jvmCore = "io.ktor:ktor-client-core-jvm:${Version.Multiplatform.ktor}"
            const val okHttp = "io.ktor:ktor-client-okhttp:${Version.Multiplatform.ktor}"
            const val androidClient = "io.ktor:ktor-client-android:${Version.Multiplatform.ktor}"
            const val jvmJson = "io.ktor:ktor-client-json-jvm:${Version.Multiplatform.ktor}"
            const val ios = "io.ktor:ktor-client-ios:${Version.Multiplatform.ktor}"
            const val iosCore = "io.ktor:ktor-client-core-native:${Version.Multiplatform.ktor}"
            const val iosJson = "io.ktor:ktor-client-json-native:${Version.Multiplatform.ktor}"
            const val commonSerialization =
                "io.ktor:ktor-client-serialization:${Version.Multiplatform.ktor}"
            const val androidSerialization =
                "io.ktor:ktor-client-serialization-jvm:${Version.Multiplatform.ktor}"
            const val logger = "io.ktor:ktor-client-logging:${Version.Multiplatform.ktor}"

            const val io = "io.ktor:ktor-io:${Version.Multiplatform.ktor}"

            // Ktor - Testing
            const val mock = "io.ktor:ktor-client-mock:${Version.Multiplatform.ktor}"
        }

        /**
         * [SqlDelight](https://github.com/cashapp/sqldelight)
         */
        object SqlDelight {
            private const val VERSION = "_"

            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"

            const val runtime = "com.squareup.sqldelight:runtime:$VERSION"
            const val coroutines = "com.squareup.sqldelight:coroutines-extensions:$VERSION"

            const val driverJvm = "com.squareup.sqldelight:sqlite-driver:$VERSION"
            const val driverJs = "com.squareup.sqldelight:sqljs-driver:$VERSION"
            const val driverIos = "com.squareup.sqldelight:native-driver:$VERSION"
            const val driverAndroid = "com.squareup.sqldelight:android-driver:$VERSION"
        }

        object Koin {
            const val common = "io.insert-koin:koin-core:${Version.Multiplatform.koin}"
            const val test = "io.insert-koin:koin-test:${Version.Multiplatform.koin}"
            const val testJunit4 = "io.insert-koin:koin-test-junit4:${Version.Multiplatform.koin}"
            const val android = "io.insert-koin:koin-android:${Version.Multiplatform.koin}"
            const val androidExt = "io.insert-koin:koin-android-ext:${Version.Multiplatform.koin}"
        }

        object Klock {
            const val common = "com.soywiz.korlibs.klock:klock:${Version.Multiplatform.klock}"
            const val android =
                "com.soywiz.korlibs.klock:klock-android:${Version.Multiplatform.klock}"
        }

        object Logger {
            const val common = "com.github.aakira:napier:${Version.Multiplatform.napier}"
            const val ios = "com.github.aakira:napier-ios:${Version.Multiplatform.napier}"
        }

        val stately = "co.touchlab:stately-common:${Version.Multiplatform.stately}"
    }

    object MultiplatformTest {
        val kotlin = Kotlin
        val mockK = MockK
        val hamkrest = Hamkrest

        object Kotlin {
            const val common = "org.jetbrains.kotlin:kotlin-test-common:${Version.kotlin}"
            const val commonAnnotations =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:${Version.kotlin}"
            const val jvmJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Version.kotlin}"
        }

        object MockK {
            const val common = "io.mockk:mockk-common:${Version.MultiplatformTest.mockK}"
            const val junit = "io.mockk:mockk:${Version.MultiplatformTest.mockK}"
            const val androidInstrumentation =
                "io.mockk:mockk-android:${Version.MultiplatformTest.mockK}"
        }

        object Hamkrest {
            const val common =
                "com.github.npryce.hamkrest:hamkrest-metadata:${Version.MultiplatformTest.hamkrest}"
            const val android =
                "com.github.npryce.hamkrest:hamkrest-jvm:${Version.MultiplatformTest.hamkrest}"
        }

        const val karmok = "co.touchlab:karmok-library:${Version.MultiplatformTest.karmok}"
    }

    object JvmTest {
        const val junit = "junit:junit:${Version.JvmTest.jUnit}"
    }

    object Android {

        val androidX = AndroidX
        val ui = Ui

        // Android
        const val desugar = "com.android.tools:desugar_jdk_libs:${Version.Android.androidDesugar}"

        object AndroidX {

            val activity = Activity
            val lifecycle = Lifecycle
            val compose = Compose

            // Core
            const val coreKtx = "androidx.core:core-ktx:${Version.Android.AndroidX.coreKtx}"
            const val appCompat =
                "androidx.appcompat:appcompat:${Version.Android.AndroidX.appCompat}"
            const val browser = "androidx.browser:browser:${Version.Android.AndroidX.browser}"

            // Navigation
            const val navigationFragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:${Version.Android.AndroidX.navigation}"
            const val navigationUiKtx =
                "androidx.navigation:navigation-ui-ktx:${Version.Android.AndroidX.navigation}"

            // Camera
            const val camera = "androidx.camera:camera-camera2:${Version.Android.AndroidX.camera}"

            // Work Manager
            val workManagerKtx =
                "androidx.work:work-runtime-ktx:${Version.Android.AndroidX.workManager}"

            object Activity {
                const val ktx =
                    "androidx.activity:activity-ktx:${Version.Android.AndroidX.activity}"
                const val compose =
                    "androidx.activity:activity-compose:${Version.Android.AndroidX.activity}"
            }

            object Lifecycle {
                const val commonJava8 =
                    "androidx.lifecycle:lifecycle-common-java8:${Version.Android.AndroidX.lifecycle}"
                const val extensions =
                    "androidx.lifecycle:lifecycle-extensions:${Version.Android.AndroidX.lifecycle}"
                const val livedata =
                    "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Android.AndroidX.lifecycle}"
                const val runtime =
                    "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Android.AndroidX.lifecycle}"
                const val viewModel =
                    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Android.AndroidX.lifecycle}"
            }

            object Compose {
                const val ui = "androidx.compose.ui:ui:${Version.Android.AndroidX.Compose.core}"

                // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
                const val foundation =
                    "androidx.compose.foundation:foundation:${Version.Android.AndroidX.Compose.core}"

                // Material Design
                const val material =
                    "androidx.compose.material:material:${Version.Android.AndroidX.Compose.core}"

                // Material design icons
                const val materialIcons =
                    "androidx.compose.material:material-icons-core:${Version.Android.AndroidX.Compose.core}"
                const val materialExt =
                    "androidx.compose.material:material-icons-extended:${Version.Android.AndroidX.Compose.core}"

                // Navigation
                const val navigation = "androidx.navigation:navigation-compose:2.4.0-beta02"

                // Tooling support (Previews, etc.)
                const val tooling =
                    "androidx.compose.ui:ui-tooling:${Version.Android.AndroidX.Compose.core}"

                // Integration with ViewModels
                const val viewmodel =
                    "androidx.lifecycle:lifecycle-viewmodel-compose:" +
                        Version.Android.AndroidX.Compose.lifecycleViewModel

                // Integration with observables
                const val livedata =
                    "androidx.compose.runtime:runtime-livedata:${Version.Android.AndroidX.Compose.core}"

                // Support
                const val accompanistSwipeToRefresh =
                    "com.google.accompanist:accompanist-swiperefresh:${Version.Android.AndroidX.Compose.accompanist}"

                const val accompanistSystemUiController =
                    "com.google.accompanist:accompanist-systemuicontroller:" +
                        Version.Android.AndroidX.Compose.accompanist

                const val accompanistInsets =
                    "com.google.accompanist:accompanist-insets:${Version.Android.AndroidX.Compose.accompanist}"
                const val accompanistInsetsUi =
                    "com.google.accompanist:accompanist-insets-ui:${Version.Android.AndroidX.Compose.accompanist}"

                const val coil = "io.coil-kt:coil-compose:${Version.Android.AndroidX.Compose.coil}"
            }
        }

        object Ui {
            // Material
            const val material =
                "com.google.android.material:material:${Version.Android.Ui.material}"

            // UI
            const val pageIndicator =
                "com.romandanylyk:pageindicatorview:${Version.Android.Ui.pageIndicatorView}"
            const val androidSvg =
                "com.caverock:androidsvg-aar:${Version.Android.Ui.androidSvg}"
            const val photoView =
                "com.github.chrisbanes:PhotoView:${Version.Android.Ui.photoView}"
            const val spinnerDatePicker =
                "com.github.drawers:SpinnerDatePicker:${Version.Android.Ui.spinnerDatePicker}"
            const val glide = "com.github.bumptech.glide:glide:${Version.Android.Ui.glide}"
        }

        const val threeTenABP =
            "com.jakewharton.threetenabp:threetenabp:${Version.Android.threeTenABP}"
        const val matomo = "org.matomo.sdk:tracker:${Version.Android.matomo}"
        const val sentry = "io.sentry:sentry-android:${Version.Android.sentry}"
        const val timber = "com.jakewharton.timber:timber:${Version.Android.timber}"

        const val sqlCipher = "net.zetetic:android-database-sqlcipher:${Version.Android.sqlCipher}"

        const val googlePlayServicesSafetyNet =
            "com.google.android.gms:play-services-safetynet:${Version.Android.googlePlayServices}"
        const val safetyNetHelper =
            "com.github.scottyab:safetynethelper:${Version.Android.safetyNetHelper}"
        const val rootBeer = "com.scottyab:rootbeer-lib:${Version.Android.rootBeer}"
        const val appAuth = "net.openid:appauth:${Version.Android.appAuth}"
    }

    object AndroidTest {

        val espresso = Espresso

        const val core = "androidx.test:core:${Version.AndroidTest.androidXTest}"
        const val coreKtx = "androidx.test:core-ktx:${Version.AndroidTest.androidXTest}"
        const val runner = "androidx.test:runner:${Version.AndroidTest.androidXTest}"
        const val rules = "androidx.test:rules:${Version.AndroidTest.androidXTest}"
        const val orchestrator = "androidx.test:orchestrator:${Version.AndroidTest.androidXTest}"

        const val junit = "androidx.test.ext:junit:${Version.AndroidTest.androidXJunit}"
        const val junitKtx = "androidx.test.ext:junit-ktx:${Version.AndroidTest.androidXJunit}"

        const val architectureCore =
            "androidx.arch.core:core-testing:${Version.AndroidTest.androidXArchitectureCoreTesting}"

        const val composeTestJunit4 =
            "androidx.compose.ui:ui-test-junit4:${Version.AndroidTest.androidXCompose}"
        const val composeTestManifest =
            "androidx.compose.ui:ui-test-manifest:${Version.AndroidTest.androidXCompose}"

        const val uiAutomator =
            "androidx.test.uiautomator:uiautomator:${Version.AndroidTest.androidXUiAutomator}"

        const val robolectric = "org.robolectric:robolectric:${Version.AndroidTest.robolectric}"

        const val kakao = "io.github.kakaocup:kakao:${Version.AndroidTest.kakao}"
        const val kakaoCompose = "io.github.kakaocup:compose:${Version.AndroidTest.kakaoCompose}"

        object Espresso {
            const val core =
                "androidx.test.espresso:espresso-core:${Version.AndroidTest.androidXEspresso}"
            const val contrib =
                "androidx.test.espresso:espresso-contrib:${Version.AndroidTest.androidXEspresso}"
            const val intents =
                "androidx.test.espresso:espresso-intents:${Version.AndroidTest.androidXEspresso}"
            const val web =
                "androidx.test.espresso:espresso-web:${Version.AndroidTest.androidXEspresso}"
        }
    }
}
