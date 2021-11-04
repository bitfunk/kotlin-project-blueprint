/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

object Version {

    val gradlePlugin = GradlePlugin
    val multiplatform = Multiplatform
    val multiplatformTest = MultiplatformTest
    val jvm = Jvm
    val jvmTest = JvmTest
    val android = Android
    val androidTest = AndroidTest
    val endToEndTest = EndToEndTest

    /**
     * [Kotlin](https://github.com/JetBrains/kotlin)
     */
    const val kotlin = "1.5.31"

    /**
     * [SQLDelight](https://github.com/cashapp/sqldelight)
     */
    const val sqlDelight = "1.5.1"

    object GradlePlugin {
        // Kotlin

        const val kotlin = Version.kotlin

        /**
         * [Gradle SQLDelight Plugin](https://github.com/cashapp/sqldelight)
         */
        const val sqlDelight = Version.sqlDelight

        // Versions
        /**
         * [Gradle Git Version Plugin](https://github.com/wmontwe/gradle-git-version)
         */
        const val gitVersion = "0.12.6"

        /**
         * [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)
         */
        const val gradleVersions = "0.38.0"

        // Android
        /**
         * [Android Gradle Plugin](https://developer.android.com/studio)
         */
        const val android = "7.0.3"

        /**
         * [Gradle Google Play Publisher Plugin](https://github.com/Triple-T/gradle-play-publisher)
         */
        const val playPublisher = "3.6.0"

        /**
         * [Gradle Sentry Android Plugin](https://github.com/getsentry/sentry-android-gradle-plugin)
         */
        const val sentryAndroid = "2.1.4"

        // Quality
        /**
         * [Detekt](https://github.com/detekt/detekt)
         */
        const val detekt = "1.18.1"
    }

    object Multiplatform {

        val kotlin = Kotlin

        object Kotlin {
            /**
             * [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
             */
            const val coroutines = "1.5.1-native-mt"

            /**
             * [Serialization](https://github.com/Kotlin/kotlinx.serialization)
             */
            const val serialization = "1.2.2"

            /**
             * [DateTime](https://github.com/Kotlin/kotlinx-datetime)
             */
            const val dateTime = "0.1.1"
        }

        /**
         * [Ktor](https://ktor.io/)
         */
        const val ktor = "1.6.2"

        /**
         * [Koin](https://github.com/InsertKoinIO/koin)
         */
        const val koin = "3.0.2"

        /**
         * [SqlDelight](https://github.com/cashapp/sqldelight)
         */
        const val sqlDelight = Version.sqlDelight

        /**
         * [klock](https://github.com/korlibs/klock)
         */
        const val klock = "2.0.7"

        /**
         * [Napier](https://github.com/AAkira/Napier)
         */
        const val napier = "1.4.1"

        /**
         * [Stately](https://github.com/touchlab/Stately)
         */
        const val stately = "1.1.7"
    }

    object MultiplatformTest {
        /**
         * [mockk](http://mockk.io)
         */
        const val mockK = "1.12.0"

        /**
         * [Hamcrest](https://github.com/npryce/hamkrest)
         */
        // FIXME there is no official multi platform release yet
        const val hamkrest = "63c8e84dd1"

        /**
         * [Karmok](https://github.com/touchlab/Karmok)
         */
        const val karmok = "0.1.8"
    }

    object Jvm {
        /**
         * [GSON](https://github.com/google/gson)
         */
        const val gson = "2.8.6"
    }

    object JvmTest {
        const val jUnit = "4.13.2"

        const val jsonAssert = "1.5.0"
    }

    object Android {

        val androidX = AndroidX
        val ui = Ui

        /**
         * [Android Desugar](https://developer.android.com/studio/write/java8-support)
         */
        const val androidDesugar = "1.0.9"

        object AndroidX {

            val compose = Compose

            /**
             * [AndroidX](https://developer.android.com/jetpack/androidx)
             */
            const val core = "1.1.0"
            const val coreKtx = "1.6.0"
            const val appCompat = "1.3.1"
            const val browser = "1.3.0"

            const val activity = "1.3.1"

            const val lifecycle = "2.3.1"
            const val navigation = "2.3.5"
            const val camera = "1.0.0-alpha03"
            const val workManager = "2.5.0"

            object Compose {
                /**
                 * [Compsoe](https://developer.android.com/jetpack/compose/setup)
                 */
                const val core = "1.0.4"

                const val activity = "1.3.1"

                const val lifecycleViewModel = "1.0.0"

                /**
                 * [Accompanist](https://google.github.io/accompanist/)
                 */
                const val accompanist = "0.20.1"

                /**
                 * [Coil (Compose Image Loader)](https://coil-kt.github.io/coil/compose/)
                 */
                const val coil = "1.3.2"
            }
        }

        object Ui {
            /**
             * [Material Android](https://github.com/material-components/material-components-android)
             */
            const val material = "1.4.0"

            /**
             * [PageIndicatorView](https://github.com/romandanylyk/PageIndicatorView)
             */
            const val pageIndicatorView = "1.0.3"

            /**
             * [Android SVG](https://github.com/BigBadaboom/androidsvg)
             */
            const val androidSvg = "1.4"

            /**
             * [PhotoView](https://github.com/Baseflow/PhotoView)
             */
            const val photoView = "2.3.0"

            /**
             * [Glide](https://github.com/bumptech/glide)
             */
            const val glide = "4.12.0"

            /**
             * [SpinnerDatePicker](https://github.com/drawers/SpinnerDatePicker)
             */
            const val spinnerDatePicker = "2.1.0"
        }

        /**
         * [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP/)
         */
        const val threeTenABP = "1.3.1"

        /**
         * [Matomo Android](https://github.com/matomo-org/matomo-sdk-android)
         */
        const val matomo = "4.1.2"

        /**
         * [Timber](https://github.com/JakeWharton/timber)
         */
        const val timber = "5.0.1"

        /**
         * [Sentry](https://github.com/getsentry/sentry-android)
         */
        const val sentry = "5.1.2"

        /**
         * [SqlCipher Android](https://www.zetetic.net/sqlcipher/sqlcipher-for-android/)
         */
        const val sqlCipher = "4.4.3"

        // Root detection
        /**
         * [Google Play Services](https://developers.google.com/android/guides/setup)
         */
        const val googlePlayServices = "17.0.1"

        /**
         * [SafetyNet Helper](https://github.com/scottyab/safetynethelper)
         */
        const val safetyNetHelper = "0.8.0"

        /**
         * [RootBeer](https://github.com/scottyab/rootbeer)
         */
        const val rootBeer = "0.1.0"

        /**
         * [AppAuth-Android](https://github.com/openid/AppAuth-Android)
         */
        const val appAuth = "0.10.0"
    }

    object AndroidTest {
        /**
         * [Android Testing](https://developer.android.com/testing)
         */
        const val androidXTest = "1.4.0"
        const val androidXJunit = "1.1.3"
        const val androidXEspresso = "3.4.0"
        const val androidXUiAutomator = "2.2.0"

        const val androidXCompose = Android.AndroidX.Compose.core

        const val androidXArchitectureCoreTesting = "2.1.0"

        const val androidXLifecycleTest = "2.1.0"

        /**
         * [Robolectric](https://github.com/robolectric/robolectric)
         */
        const val robolectric = "4.5.1"

        /**
         * [Kakao](https://github.com/KakaoCup/Kakao)
         */
        const val kakao = "3.0.5"

        /**
         * [Kakao Compose](https://github.com/KakaoCup/Compose)
         */
        const val kakaoCompose = "0.0.2"
    }

    object EndToEndTest {
        /**
         * [Appium](https://github.com/appium/java-client)
         */
        const val appium = "6.0.0"

        /**
         * [Selenide](https://github.com/selenide/selenide)
         */
        const val selenide = "5.2.4"

        /**
         * [JUnit5 Jupiter](https://github.com/junit-team/junit5/)
         */
        const val junitJupiter = "5.5.1"

        /**
         * [JUnit5 PlatformRunner](https://github.com/junit-team/junit5/)
         */
        const val junitPlatformRunner = "1.5.2"

        /**
         * [Retrofit](https://github.com/square/retrofit)
         */
        const val retrofit = "2.6.3"

        const val kotlinSerialization = Multiplatform.Kotlin.serialization
    }
}
