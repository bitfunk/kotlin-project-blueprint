/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

plugins {
    id("org.jetbrains.gradle.apple.applePlugin") version "212.4638.14-0.13.1"
}

apple {
    iosApp {
        productName = "Blueprint"

        sceneDelegateClass = "SceneDelegate"
        launchStoryboard = "LaunchScreen"

        // productInfo["NSAppTransportSecurity"] = mapOf("NSAllowsArbitraryLoads" to true)
        // buildSettings.OTHER_LDFLAGS("")

        dependencies {
            // implementation(project(":shared"))
        }
    }
}
