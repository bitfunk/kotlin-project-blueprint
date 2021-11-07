/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

plugins {
    id("eu.upwolf.gradle.blueprint.configuration.ios.app")
}

apple {
    iosApp {
        productName = "Blueprint"

        // productInfo["NSAppTransportSecurity"] = mapOf("NSAllowsArbitraryLoads" to true)
        // buildSettings.OTHER_LDFLAGS("")

        dependencies {
            implementation(project(":feature:home"))
        }
    }
}
