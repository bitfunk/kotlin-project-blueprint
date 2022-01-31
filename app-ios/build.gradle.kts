/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationAppIos)
}

apple {
    iosApp {
        productName = "Blueprint"

        embedFrameworks = true

        dependencies {
            implementation(projects.feature.root.component)
            implementation(projects.feature.splash.resources)
        }
    }
}
