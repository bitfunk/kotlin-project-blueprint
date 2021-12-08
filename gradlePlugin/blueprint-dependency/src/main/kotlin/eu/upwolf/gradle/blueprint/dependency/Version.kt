/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

object Version {

    val multiplatform = Multiplatform
    val multiplatformTest = MultiplatformTest
    val jvmTest = JvmTest
    val android = Android
    val androidTest = AndroidTest

    object Multiplatform {

        /**
         * [Ktor](https://ktor.io/)
         */
        const val ktor = "1.6.2"

        /**
         * [Koin](https://github.com/InsertKoinIO/koin)
         */
        const val koin = "3.1.4"

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

    object JvmTest {
        const val jUnit = "4.13.2"
    }

    object Android {

        val androidX = AndroidX

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
            const val coreKtx = "1.7.0"
            const val appCompat = "1.4.0"
            const val browser = "1.3.0"

            const val activity = "1.3.1"

            const val lifecycle = "2.3.1"
            const val navigation = "2.3.5"
            const val camera = "1.0.0-alpha03"
            const val workManager = "2.5.0"

            object Compose {

                const val compiler = "1.1.0-beta04"

                /**
                 * [Coil (Compose Image Loader)](https://coil-kt.github.io/coil/compose/)
                 */
                const val coil = "1.3.2"
            }
        }

        /**
         * [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP/)
         */
        const val threeTenABP = "1.3.1"

        /**
         * [Timber](https://github.com/JakeWharton/timber)
         */
        const val timber = "5.0.1"

        /**
         * [SqlCipher Android](https://www.zetetic.net/sqlcipher/sqlcipher-for-android/)
         */
        const val sqlCipher = "4.4.3"
    }

    object AndroidTest {
        /**
         * [Android Testing](https://developer.android.com/testing)
         */
        const val androidXTest = "1.4.0"
        const val androidXJunit = "1.1.3"
        const val androidXEspresso = "3.4.0"
        const val androidXUiAutomator = "2.2.0"

        const val androidXArchitectureCoreTesting = "2.1.0"

        const val androidXLifecycleTest = "2.1.0"

        /**
         * [Robolectric](https://github.com/robolectric/robolectric)
         */
        const val robolectric = "4.5.1"
    }
}
